package com.simp.service.shared.domain.model;

import java.time.Instant;

public record DateRange(Instant from, Instant to) {
    public boolean isInstantIn(Instant value) {
        return from.isBefore(value) && to.isAfter(value);
    }
}
