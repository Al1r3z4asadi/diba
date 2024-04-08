package com.diba.beneficiary.shared.messages.events;

public record EventMetadata(
        byte[] userMetadata,
        String eventId,
        long streamPosition,
        String eventType,
        long logPosition,
        String StreamId

) {
}