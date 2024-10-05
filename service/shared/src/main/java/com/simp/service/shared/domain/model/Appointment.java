package com.simp.service.shared.domain.model;

import java.time.Instant;

public interface Appointment {
    long id();

    long creator();

    long timetable();

    Instant time();
}
