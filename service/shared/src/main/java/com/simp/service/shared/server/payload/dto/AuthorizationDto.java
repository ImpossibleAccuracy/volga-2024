package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.Authorization;

import java.util.List;

public record AuthorizationDto(
        @JsonProperty("account")
        AccountDto account,
        @JsonProperty("roles")
        List<String> roles
) implements Authorization {
}
