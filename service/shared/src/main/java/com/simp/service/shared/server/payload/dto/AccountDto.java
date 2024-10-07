package com.simp.service.shared.server.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.domain.model.Account;

public record AccountDto(
        @JsonProperty("id")
        long id,
        @JsonProperty("firstName")
        String firstName,
        @JsonProperty("lastName")
        String lastName,
        @JsonProperty("username")
        String username
) implements Account {
}
