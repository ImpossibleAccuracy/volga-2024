package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record DateRangeDto(
        @JsonProperty("from")
        Instant from,
        @JsonProperty("to")
        Instant to
) {
}
