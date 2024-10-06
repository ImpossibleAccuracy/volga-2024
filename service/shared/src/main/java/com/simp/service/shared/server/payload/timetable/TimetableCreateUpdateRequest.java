package com.simp.service.shared.server.payload.timetable;

import java.time.Instant;

public record TimetableCreateUpdateRequest(long hospitalId, long doctorId, Instant from, Instant to, String room) {
}
