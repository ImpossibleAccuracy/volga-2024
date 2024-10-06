package com.simp.service.shared.server.payload.timetable;

import java.time.Instant;

public record CreateAppointmentsRequest(Instant time) {
}
