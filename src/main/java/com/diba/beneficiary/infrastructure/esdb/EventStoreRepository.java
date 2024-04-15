package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.http.ETag;
import com.diba.beneficiary.shared.messages.events.IEvent;
import com.diba.beneficiary.shared.messages.utils.MessageSerializer;
import com.diba.beneficiary.core.models.AbstractAggregate;
import com.eventstore.dbclient.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class EventStoreRepository<Entity extends AbstractAggregate<Event, Id>, Event extends IEvent, Id>
        implements IEventStoreDBRepository<Entity, Id> {

    private final EventStoreDBClient eventStore;
    private final Function<Id, String> mapToStreamId;
    private final Supplier<Entity> getEmpty;

    public EventStoreRepository(
            EventStoreDBClient eventStore,
            Function<Id, String> mapToStreamId,
            Supplier<Entity> getEmpty
    ) {

        this.eventStore = eventStore;
        this.mapToStreamId = mapToStreamId;
        this.getEmpty = getEmpty;
    }

    public CompletableFuture<Entity> Find(UUID id) {
        return null;
    }

    public ETag Add(Entity aggregate) {

        return appendEvents(
                aggregate,
                AppendToStreamOptions.get().expectedRevision(ExpectedRevision.noStream())
        );
    }

    public CompletableFuture<BigInteger> Update(Entity aggregate, BigInteger expectedRevision) {
        return null;
    }

    public CompletableFuture<BigInteger> Delete(Entity aggregate, BigInteger expectedRevision) {
        return null;
    }

    public Optional<Entity> get(Id id) throws Exception {
        String streamId = mapToStreamId.apply(id);
        var events = getEvents(streamId);
        // TODO : SET<PRODUCTS> p
        // TODO : REPOSITORY
        if (events.isEmpty())
            return Optional.empty();
        var current = this.getEmpty.get();
        for (var event : events.get()) {
            current.when(event);
        }
        return Optional.ofNullable(current);
    }

    public ETag appendEvents(Entity entity, AppendToStreamOptions appendOptions) {
        var streamId = mapToStreamId.apply(entity.id());
        var events = Arrays.stream(entity.dequeueUncommittedEvents())
                .map(MessageSerializer::serialize);

        try {
            var result = eventStore.appendToStream(
                    streamId,
                    appendOptions,
                    events.iterator()
            ).get();
//            return null ;
            return toETag(result.getNextExpectedRevision());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public ETag getAndUpdate(Consumer<Entity> handle, Id id, long expectedRevision) throws Exception {
        var streamId = mapToStreamId.apply(id);
        var entity = get(id).orElseThrow(
                () -> new RuntimeException("Stream with id %s was not found".formatted(streamId))
        );
        handle.accept(entity);
        return appendEvents(entity, AppendToStreamOptions.get());
    }

    //privates
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

    private ETag toETag(ExpectedRevision nextExpectedRevision) throws NoSuchFieldException, IllegalAccessException {
        var field = nextExpectedRevision.getClass().getDeclaredField("version");
        field.setAccessible(true);

        return ETag.weak(field.get(nextExpectedRevision));
    }

}
