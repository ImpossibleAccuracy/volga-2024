package com.simp.service.shared.server.payload.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatePaginationRequest {
    @JsonProperty("from")
    public Instant from = Instant.ofEpochSecond(0);
    @JsonProperty("to")
    public Instant to = Instant.now();
}
