package com.simp.service.shared.server.payload.shared;

import java.time.Instant;

public record DatePaginationRequest(Instant from, Instant to) {
}
