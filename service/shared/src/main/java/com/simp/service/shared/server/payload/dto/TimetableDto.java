package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.Timetable;

import java.time.Instant;

public record TimetableDto(
        @JsonProperty("id")
        long id,
        @JsonProperty("hospital")
        long hospital,
        @JsonProperty("doctor")
        long doctor,
        @JsonProperty("from")
        Instant from,
        @JsonProperty("to")
        Instant to,
        @JsonProperty("room")
        long room
) implements Timetable {
}
