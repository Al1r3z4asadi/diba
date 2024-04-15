package com.diba.beneficiary.infrastructure.esdb.subscriptions;

import java.time.OffsetDateTime;

public record CheckpointStored(
        String subscriptionId,
        long position,
        OffsetDateTime checkPointedAt
) {
}
