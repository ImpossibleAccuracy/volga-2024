package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.Hospital;

public record HospitalDto(
        @JsonProperty("id")
        long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("address")
        String address,
        @JsonProperty("contactPhone")
        String contactPhone
) implements Hospital {
}
