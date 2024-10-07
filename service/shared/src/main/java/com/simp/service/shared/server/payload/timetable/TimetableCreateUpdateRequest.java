package com.simp.service.shared.server.payload.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Instant;

public record TimetableCreateUpdateRequest(
        @PositiveOrZero
        @JsonProperty("hospitalId")
        long hospitalId,

        @PositiveOrZero
        @JsonProperty("doctorId")
        long doctorId,

        @PositiveOrZero
        @JsonProperty("from")
        Instant from,

        @PositiveOrZero
        @JsonProperty("to")
        Instant to,

        @NotBlank
        @JsonProperty("room")
        String room
) {
}
