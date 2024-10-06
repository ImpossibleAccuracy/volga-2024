package com.simp.service.shared.server.payload.dto;

import java.time.Instant;

public record DateRangeDto(Instant from, Instant to) {
}
