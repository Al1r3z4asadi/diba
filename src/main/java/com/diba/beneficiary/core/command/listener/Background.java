package com.diba.beneficiary.core.command.listener;

import com.diba.beneficiary.core.command.ICommandBus;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.context.SmartLifecycle;

public class Background implements SmartLifecycle {

    private final EventStoreDBClient _client;
    private final ICommandBus _bus ;
    private ESCommandSubscriber subscription;



    public Background(EventStoreDBClient client, ICommandBus bus){
        this._client = client ;
        this._bus = bus ;
    }

    @Override
    public void start() {
        try {
            subscription = new ESCommandSubscriber(
                    this._client,
                    this._bus
            );
            subscription.subscribeToAll();
        } catch (Throwable e) {
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
