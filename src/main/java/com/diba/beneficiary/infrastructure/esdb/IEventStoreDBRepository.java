package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.http.ETag;
import com.diba.beneficiary.core.models.AbstractAggregate;
import com.diba.beneficiary.core.models.Aggregate;
import com.eventstore.dbclient.AppendToStreamOptions;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface IEventStoreDBRepository<Entity      , Id> {
    CompletableFuture<Entity> Find(UUID id);
    ETag Add(Entity aggregate);
    CompletableFuture<BigInteger> Update(Entity aggregate, BigInteger expectedRevision);
    CompletableFuture<BigInteger> Delete(Entity aggregate, BigInteger expectedRevision );
    Optional<Entity> get(Id id) ;
    ETag appendEvents(Entity entity, AppendToStreamOptions appendOptions);
    ETag getAndUpdate(Consumer<Entity> handle, Id id, long expectedRevision);

}
