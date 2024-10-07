package com.simp.service.shared.domain.model;

import java.time.Instant;

public interface History {
    long id();

    Instant date();

    long patient();

    long doctor();

    long room();

    String data();
}
