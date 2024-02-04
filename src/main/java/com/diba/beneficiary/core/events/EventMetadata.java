package com.diba.beneficiary.core.events;


public record EventMetadata(
        String correlationId,
        String causationId ,
        String eventId,
        long streamPosition,
        long logPosition,
        String eventType
) {
}