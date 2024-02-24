package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.service.eventbus.IEventBus;
import com.diba.beneficiary.infrastructure.esdb.subscriptions.EventStoreDBSubscriptionToAll;
import com.diba.beneficiary.infrastructure.esdb.subscriptions.ISubscriptionCheckpointRepository;
import com.eventstore.dbclient.EventStoreDBClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

public class EventListener implements SmartLifecycle {

    private final ISubscriptionCheckpointRepository subscriptionCheckpointRepository;
    private final EventStoreDBClient eventStore;
    private final IEventBus eventBus;
    private EventStoreDBSubscriptionToAll subscription;

    private final Logger logger = LoggerFactory.getLogger(EventListener.class);
    public EventListener(
            EventStoreDBClient eventStore,
            ISubscriptionCheckpointRepository subscriptionCheckpointRepository,
            IEventBus eventBus
    ) {
        this.eventStore = eventStore;
        this.subscriptionCheckpointRepository = subscriptionCheckpointRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void start() {
        try {
            subscription = new EventStoreDBSubscriptionToAll(
                    this.eventStore,
                    this.eventBus ,
                    this.subscriptionCheckpointRepository
                    );
            subscription.subscribeToAll();
        } catch (Throwable e) {
            logger.error("Failed to start Subscription to All", e);
        }
    }

    @Override
    public void stop() {
        stop(() -> {});
    }

    @Override
    public boolean isRunning() {
        return subscription != null && subscription.isRunning();
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        if (!isRunning()) return;

        subscription.stop();

        callback.run();
    }
}
