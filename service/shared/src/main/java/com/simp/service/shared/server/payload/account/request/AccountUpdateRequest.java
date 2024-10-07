package com.simp.service.shared.server.payload.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AccountUpdateRequest(
        @NotBlank
        @JsonProperty("lastName")
        String lastName,

        @NotBlank
        @JsonProperty("firstName")
        String firstName,

        @NotBlank
        @JsonProperty("password")
        String password
) {
}
