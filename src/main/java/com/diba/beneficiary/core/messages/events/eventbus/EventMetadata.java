package com.diba.beneficiary.core.messages.events.eventbus;

public record EventMetadata(
        byte[] userMetadata,
        String eventId,
        long streamPosition,
        String eventType,

        long logPosition
) {
}