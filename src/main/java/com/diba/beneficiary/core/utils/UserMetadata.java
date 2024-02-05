package com.diba.beneficiary.core.utils;

public record UserMetadata(
        String correlationId,
        String causationId
) {
}
