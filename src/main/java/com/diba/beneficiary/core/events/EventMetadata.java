package com.diba.beneficiary.core.events;

public record EventMetadata(
        byte[] userMetadata,
        String eventId,
        long streamPosition,
        long logPosition,
        String eventType
) {
}