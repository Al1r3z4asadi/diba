package com.diba.beneficiary.shared.messages.utils;

public record UserMetadata(
        String correlationId,
        String causationId
) {
}
