package com.simp.service.shared.server.payload.account.response;

import com.simp.service.shared.server.payload.dto.AccountDto;

public record AuthResponse(String token, AccountDto account) {
}
