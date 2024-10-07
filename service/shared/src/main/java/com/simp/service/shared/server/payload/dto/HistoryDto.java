package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.History;

import java.time.Instant;

public record HistoryDto(
        @JsonProperty("id")
        long id,
        @JsonProperty("date")
        Instant date,
        @JsonProperty("pacient")
        long patient,
        @JsonProperty("doctor")
        long doctor,
        @JsonProperty("room")
        long room,
        @JsonProperty("data")
        String data
) implements History {
}
