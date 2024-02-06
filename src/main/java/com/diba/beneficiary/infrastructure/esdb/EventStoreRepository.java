package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.events.eventbus.IEvent;
import com.diba.beneficiary.core.models.AbstractAggregate;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Repository
public class EventStoreRepository <Entity extends AbstractAggregate<IEvent, Id>, Event, Id>
        implements IEventStoreDBRepository<AbstractAggregate> {

    private final EventStoreDBClient _eventStore;

    public EventStoreRepository(EventStoreDBClient eventStore) {
        _eventStore = eventStore;
    }




    @Override
    public CompletableFuture<AbstractAggregate> Find(UUID id) {
        return null;
    }

    @Override
    public CompletableFuture<BigInteger> Add(AbstractAggregate aggregate) {
        return null;
    }

    @Override
    public CompletableFuture<BigInteger> Update(AbstractAggregate aggregate, BigInteger expectedRevision) {
        return null;
    }

    @Override
    public CompletableFuture<BigInteger> Delete(AbstractAggregate aggregate, BigInteger expectedRevision) {
        return null;
    }






}
