package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.models.Aggregate;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IEventStoreDBRepository<Entity extends Aggregate> {
    CompletableFuture<Entity> Find(UUID id);
    CompletableFuture<BigInteger> Add(Entity aggregate);
    CompletableFuture<BigInteger> Update(Entity aggregate, BigInteger expectedRevision);
    CompletableFuture<BigInteger> Delete(Entity aggregate, BigInteger expectedRevision );
}
