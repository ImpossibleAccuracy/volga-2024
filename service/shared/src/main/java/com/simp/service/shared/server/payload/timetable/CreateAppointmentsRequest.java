package com.simp.service.shared.server.payload.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record CreateAppointmentsRequest(@JsonProperty("time") Instant time) {
}
