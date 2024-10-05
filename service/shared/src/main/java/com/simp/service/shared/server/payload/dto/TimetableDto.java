package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.Timetable;

import java.time.Instant;

public record TimetableDto(
        long id,
        long hospital,
        long doctor,
        Instant from,
        Instant to,
        long room
) implements Timetable {
}
