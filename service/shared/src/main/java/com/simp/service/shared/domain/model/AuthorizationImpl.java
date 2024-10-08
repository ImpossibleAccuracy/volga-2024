package com.simp.service.shared.domain.model;

import com.simp.service.shared.domain.security.UserRole;

import java.util.List;

public record AuthorizationImpl(Account account, List<UserRole> roles) implements Authorization {
}
