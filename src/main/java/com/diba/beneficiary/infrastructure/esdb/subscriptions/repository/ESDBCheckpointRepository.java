package com.diba.beneficiary.infrastructure.esdb.subscriptions.repository;

import com.diba.beneficiary.infrastructure.esdb.subscriptions.CheckpointStored;
import com.diba.beneficiary.infrastructure.esdb.subscriptions.ISubscriptionCheckpointRepository;
import com.diba.beneficiary.shared.messages.utils.MessageSerializer;
import com.eventstore.dbclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

@Component
public final class ESDBCheckpointRepository implements ISubscriptionCheckpointRepository {
    private final EventStoreDBClient eventStore;
    private final Logger logger = LoggerFactory.getLogger(ESDBCheckpointRepository.class);

    public ESDBCheckpointRepository(
            EventStoreDBClient eventStore
    ) {
        this.eventStore = eventStore;
    }

    public Optional<Long> load(String subscriptionId) {
        var streamName = getCheckpointStreamName(subscriptionId);

        var readOptions = ReadStreamOptions.get()
                .backwards()
                .fromEnd();

        try {
            return eventStore.readStream(streamName, readOptions)
                    .get()
                    .getEvents()
                    .stream()
                    .map(e -> MessageSerializer.<CheckpointStored>deserialize(e).map(ch -> ch.position()))
                    .findFirst()
                    .orElse(Optional.empty());

        } catch (Throwable e) {
            Throwable innerException = e.getCause();

            if (!(innerException instanceof StreamNotFoundException)) {
                logger.error("Failed to load checkpoint", e);
                throw new RuntimeException(e);
            }
            return Optional.empty();
        }
    }

    public void store(String subscriptionId, long position) {
        var event = MessageSerializer.serialize(
                new CheckpointStored(subscriptionId, position, OffsetDateTime.now())
        );

        var streamName = getCheckpointStreamName(subscriptionId);

        try {
            eventStore.appendToStream(
                    streamName,
                    AppendToStreamOptions.get().expectedRevision(ExpectedRevision.any()),
                    event
            ).get();
        } catch (Throwable e) {
            if (!(e.getCause() instanceof WrongExpectedVersionException))
                throw new RuntimeException(e);

            var keepOnlyLastEvent = new StreamMetadata();
            keepOnlyLastEvent.setMaxCount(1L);

            try {
                eventStore.setStreamMetadata(
                        streamName,
                        AppendToStreamOptions.get().expectedRevision(ExpectedRevision.noStream()),
                        keepOnlyLastEvent
                ).get();

                eventStore.appendToStream(
                        streamName,
                        AppendToStreamOptions.get().expectedRevision(ExpectedRevision.noStream()),
                        event
                ).get();
            } catch (Exception exception) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String getCheckpointStreamName(String subscriptionId) {
        return "checkpoint_%s".formatted(subscriptionId);
    }
}
