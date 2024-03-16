package com.diba.beneficiary.events;

public record EventMetadata(
        byte[] userMetadata,
        String eventId,
        long streamPosition,
        String eventType,
        long logPosition
) {
}