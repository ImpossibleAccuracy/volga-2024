package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.Authorization;

import java.util.List;

public record AuthorizationDto(AccountDto account, List<String> roles) implements Authorization {
}
