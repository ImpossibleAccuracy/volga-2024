package com.simp.service.shared.server.payload.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ValidateTokenRequest(
        @NotBlank
        @JsonProperty("accessToken")
        String accessToken
) {
}
