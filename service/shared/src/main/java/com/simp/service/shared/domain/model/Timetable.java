package com.simp.service.shared.domain.model;

import java.time.Instant;

public interface Timetable {
    long id();

    long hospital();

    long doctor();

    Instant from();

    Instant to();

    long room();
}
