package com.diba.beneficiary.core.command.listener;

import com.diba.beneficiary.core.command.ICommandBus;
import com.diba.beneficiary.core.command.Ihandlers.IBeneficiaryCommandHandler;
import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ResolvedEvent;
import com.eventstore.dbclient.Subscription;
import com.eventstore.dbclient.SubscriptionListener;
import org.springframework.retry.support.RetryTemplate;

public class ESCommandSubscriber {

    private final ICommandBus _bus ;
    private boolean isRunning;
    private Subscription subscription;
    private final EventStoreDBClient _eventStoreClient;
    private SubscriptionOption subscriptionOptions;

    private final RetryTemplate retryTemplate = RetryTemplate.builder()
            .infiniteRetry()
            .exponentialBackoff(100, 2, 5000)
            .build();
    public  ESCommandSubscriber(EventStoreDBClient eventStoreClient , ICommandBus bus ){
        this._bus = bus ;
        this._eventStoreClient = eventStoreClient;

    }
    private final SubscriptionListener listener = new SubscriptionListener() {
        @Override
        public void onEvent(Subscription subscription, ResolvedEvent event) {
            try {
                _bus.handleEvent(event);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void onCancelled(Subscription subscription, Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    };



    public void subscribeToAll() {
        subscribeToAll(SubscriptionOption.getDefault());
    }

    void subscribeToAll(SubscriptionOption subscriptionOptions) {
        this.subscriptionOptions = subscriptionOptions;

        try {
            retryTemplate.execute(context -> {

                    subscriptionOptions.subscribeToAllOptions()
                            .fromEnd();
                subscription = _eventStoreClient.subscribeToAll(
                        listener,
                        subscriptionOptions.subscribeToAllOptions()
                ).get();
                return null;
            });
        } catch (Throwable e) {
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

}
