package com.simp.service.shared.server.payload.hospital;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record HospitalCreateUpdateRequest(
        @NotBlank
        @JsonProperty("name")
        String name,

        @NotBlank
        @JsonProperty("address")
        String address,

        @NotBlank
        @JsonProperty("contactPhone")
        String contactPhone,

        @NotEmpty
        @JsonProperty("rooms")
        List<String> rooms
) {
}
