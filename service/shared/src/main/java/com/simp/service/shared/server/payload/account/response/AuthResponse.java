package com.simp.service.shared.server.payload.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simp.service.shared.server.payload.dto.AccountDto;

public record AuthResponse(
        @JsonProperty("token")
        String token,

        @JsonProperty("account")
        AccountDto account
) {
}
