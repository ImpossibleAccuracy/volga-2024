package com.simp.service.shared.server.payload.history;

import java.time.Instant;

public record HistoryCreateUpdateRequest(
        Instant date,
        long patientId,
        long hospitalId,
        long doctorId,
        String room,
        String data
) {
}
