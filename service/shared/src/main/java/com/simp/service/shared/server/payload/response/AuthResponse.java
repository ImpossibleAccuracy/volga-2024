package com.simp.service.shared.server.payload.response;

import com.simp.service.shared.domain.model.Account;

public record AuthResponse(String token, Account account) {
}
