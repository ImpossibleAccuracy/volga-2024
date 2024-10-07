package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.Appointment;

import java.time.Instant;

public record AppointmentDto(
        @JsonProperty("id")
        long id,
        @JsonProperty("creator")
        long creator,
        @JsonProperty("timetable")
        long timetable,
        @JsonProperty("time")
        Instant time
) implements Appointment {
}

