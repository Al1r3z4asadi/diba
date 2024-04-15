package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.http.ETag;
import com.eventstore.dbclient.AppendToStreamOptions;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface IEventStoreDBRepository<Entity, Id> {
    CompletableFuture<Entity> Find(UUID id);

    ETag Add(Entity aggregate);

    Optional<Entity> get(Id id) throws Exception;

    ETag appendEvents(Entity entity, AppendToStreamOptions appendOptions);

    ETag getAndUpdate(Consumer<Entity> handle, Id id, long expectedRevision) throws Exception;

}
