package com.simp.service.shared.server.payload.history;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Instant;

public record HistoryCreateUpdateRequest(
        @JsonProperty("date")
        Instant date,

        @PositiveOrZero
        @JsonProperty("pacientId")
        long patientId,

        @PositiveOrZero
        @JsonProperty("hospitalId")
        long hospitalId,

        @PositiveOrZero
        @JsonProperty("doctorId")
        long doctorId,

        @NotBlank
        @JsonProperty("room")
        String room,

        @NotBlank
        @JsonProperty("data")
        String data
) {
}
