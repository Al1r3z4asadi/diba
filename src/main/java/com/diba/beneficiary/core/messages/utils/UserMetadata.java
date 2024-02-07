package com.diba.beneficiary.core.messages.utils;

public record UserMetadata(
        String correlationId,
        String causationId
) {
}
