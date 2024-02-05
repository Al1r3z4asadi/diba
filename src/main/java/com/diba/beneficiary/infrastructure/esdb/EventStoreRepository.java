package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.models.Aggregate;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class EventStoreRepository<Entity extends Aggregate> implements IEventStoreDBRepository<Entity> {
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
}
