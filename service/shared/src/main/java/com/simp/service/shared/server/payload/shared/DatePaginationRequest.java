package com.simp.service.shared.server.payload.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatePaginationRequest {
    public Instant from = Instant.ofEpochSecond(0);
    public Instant to = Instant.now();
}
