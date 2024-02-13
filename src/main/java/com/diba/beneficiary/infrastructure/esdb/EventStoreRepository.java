package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.http.ETag;
import com.diba.beneficiary.shared.messages.events.IEvent;
import com.diba.beneficiary.shared.messages.utils.MessageSerializer;
import com.diba.beneficiary.core.models.AbstractAggregate;
import com.eventstore.dbclient.*;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


@Repository
public class EventStoreRepository <Entity extends AbstractAggregate<IEvent, Id>, Event, Id>
        implements IEventStoreDBRepository<Entity , Id> {
    private final EventStoreDBClient eventStore;
    private Class<Entity> entityType;

    public EventStoreRepository(EventStoreDBClient eventStore)
    {

        this.eventStore = eventStore;

    }

//    Type superClass = getClass().getGenericSuperclass();
//    ParameterizedType parameterizedType = (ParameterizedType) superClass;
//
//        this.entityType = (Class<Entity>) parameterizedType.getActualTypeArguments()[0];

    @Override
    public CompletableFuture<Entity> Find(UUID id) {
        return null;
    }

    @Override
    public CompletableFuture<BigInteger> Add(Entity aggregate) {
        return null;
    }

    @Override
    public CompletableFuture<BigInteger> Update(Entity aggregate, BigInteger expectedRevision) {
        return null;
    }

    @Override
    public CompletableFuture<BigInteger> Delete(Entity aggregate, BigInteger expectedRevision) {
        return null;
    }

    @Override
    public Optional<Entity> get(Id id) {
        String streamId = mapToStreamId((UUID) id);
        var events = getEvents(streamId);
        if (events.isEmpty())
            return Optional.empty();
        var current = createEntityInstance();
        for (var event : events.get()) {
            current.when((IEvent) event);
        }
        return Optional.ofNullable(current);
    }

    @Override
    public ETag appendEvents(Entity entity, AppendToStreamOptions appendOptions) {
        var streamId =  mapToStreamId((UUID) entity.id());
        var events = Arrays.stream(entity.dequeueUncommittedEvents())
                .map(MessageSerializer::serialize);

        try {
            var result = eventStore.appendToStream(
                    streamId,
                    appendOptions,
                    events.iterator()
            ).get();

            return toETag(result.getNextExpectedRevision());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ETag getAndUpdate(Consumer<Entity> handle, Id id, long expectedRevision) {
        var streamId =  mapToStreamId((UUID) id);;
        var entity = get(id).orElseThrow(
                () -> new RuntimeException("Stream with id %s was not found".formatted(streamId))
        );

        handle.accept(entity);

        return appendEvents(entity, AppendToStreamOptions.get().expectedRevision(expectedRevision));
    }


//    privates
    private Optional<List<Event>> getEvents(String streamId) {
        ReadResult result;
        try {
            result = eventStore.readStream(streamId, ReadStreamOptions.get()).get();
        } catch (Throwable e) {
            Throwable innerException = e.getCause();

            if (innerException instanceof StreamNotFoundException) {
                return Optional.empty();
            }
            throw new RuntimeException(e);
        }

        var events = result.getEvents().stream()
                .map(MessageSerializer::<Event>deserialize)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return Optional.of(events);
    }
    private Entity createEntityInstance() {
        try {
            return entityType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create an instance of Entity", e);
        }
    }
    private String mapToStreamId(UUID id) {
        return "%s-%s".formatted(this.entityType,id);
    }
    //This ugly hack is needed as ESDB Java client from v4 doesn't allow to access or serialise version in an elegant manner
    private ETag toETag(ExpectedRevision nextExpectedRevision) throws NoSuchFieldException, IllegalAccessException {
        var field = nextExpectedRevision.getClass().getDeclaredField("version");
        field.setAccessible(true);

        return ETag.weak(field.get(nextExpectedRevision));
    }

}
