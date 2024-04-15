package com.diba.beneficiary.infrastructure.esdb.subscriptions;

import java.util.Optional;

public interface ISubscriptionCheckpointRepository {
    Optional<Long> load(String subscriptionId);

    void store(String subscriptionId, long position);
}
