package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.Appointment;

import java.time.Instant;

public record AppointmentDto(long id, long creator, long timetable, Instant time) implements Appointment {
}

