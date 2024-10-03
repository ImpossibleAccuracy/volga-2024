package com.simp.service.shared.server.payload.history;

import java.util.Date;

public record HistoryCreateUpdateRequest(
        Date date,
        int pacientId,
        int hospitalId,
        int doctorId,
        String room,
        String data
) {
}
