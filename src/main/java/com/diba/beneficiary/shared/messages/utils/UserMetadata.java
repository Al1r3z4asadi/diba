package com.diba.beneficiary.shared.messages.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record UserMetadata(
        String correlationId,
        String causationId
) {
}
