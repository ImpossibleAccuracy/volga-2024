package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.History;

import java.time.Instant;

public record HistoryDto(long id, Instant date, long patient, long doctor, long room, String data) implements History {
}
