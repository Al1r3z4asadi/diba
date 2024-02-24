package com.diba.beneficiary.infrastructure.esdb.subscriptions;

import com.diba.beneficiary.core.service.eventbus.IEventBus;
import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
import com.diba.beneficiary.shared.messages.utils.MessageTypeMapper;
import com.eventstore.dbclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.support.RetryTemplate;

public class EventStoreDBSubscriptionToAll {
    private final EventStoreDBClient eventStoreClient;
    private final IEventBus eventBus;
    private final ISubscriptionCheckpointRepository checkpointRepository;

    private Subscription subscription;
    private boolean isRunning;
    private final Logger logger = LoggerFactory.getLogger(EventStoreDBSubscriptionToAll.class);

    private final RetryTemplate retryTemplate = RetryTemplate.builder()
            .infiniteRetry()
            .exponentialBackoff(100, 2, 5000)
            .build();

    private final SubscriptionListener listener = new SubscriptionListener() {
        @Override
        public void onEvent(Subscription subscription, ResolvedEvent event) {
            try {
                handleEvent(event);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onCancelled(Subscription subscription, Throwable throwable) {
            logger.error("Subscription was dropped", throwable);

            throw new RuntimeException(throwable);
        }
    };

    public EventStoreDBSubscriptionToAll(
            EventStoreDBClient eventStoreClient,
            IEventBus eventBus ,
            ISubscriptionCheckpointRepository checkpointRepository
    ) {
        this.eventStoreClient = eventStoreClient;
        this.checkpointRepository = checkpointRepository;
        this.eventBus = eventBus;
    }

    public void subscribeToAll() {
        subscribeToAll("internal");
    }

    void subscribeToAll(String s) {

        try {
            retryTemplate.execute(context -> {
                logger.info("Subscribing to all '%s'".formatted(s));

                subscription = eventStoreClient.subscribeToAll(
                        listener
                ).get();
                return null;
            });
        } catch (Throwable e) {
            logger.error("Error while starting subscription", e);
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (!isRunning)
            return;

        isRunning = false;
        subscription.stop();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    private void handleEvent(ResolvedEvent resolvedEvent) {
        if (isEventWithEmptyData(resolvedEvent))
            return;

        var eventClass = MessageTypeMapper.toClass(resolvedEvent.getEvent().getEventType());
        var streamEvent = eventClass.flatMap(c -> MessageEnvelope.of(c, resolvedEvent));

        if (streamEvent.isEmpty()) {
            logger.warn("Couldn't deserialize event with id: %s".formatted(resolvedEvent.getEvent().getEventId()));
            return;
        }

        eventBus.publish((MessageEnvelope<Message>) streamEvent.get());
    }

    private boolean isEventWithEmptyData(ResolvedEvent resolvedEvent) {
        if (resolvedEvent.getEvent().getEventData().length != 0) return false;

        logger.info("Event without data received");
        return true;
    }
}
