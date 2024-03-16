package com.diba.beneficiary.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record UserMetadata(
        String correlationId,
        String causationId
) {
}
