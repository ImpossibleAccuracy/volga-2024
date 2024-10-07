package com.simp.service.shared.server.payload.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AccountCreateUpdateRequest(
        @NotBlank
        @JsonProperty("lastName")
        String lastName,

        @NotBlank
        @JsonProperty("firstName")
        String firstName,

        @NotBlank
        @JsonProperty("username")
        String username,

        @NotBlank
        @JsonProperty("password")
        String password,

        @NotEmpty
        @JsonProperty("roles")
        List<String> roles
) {
}
