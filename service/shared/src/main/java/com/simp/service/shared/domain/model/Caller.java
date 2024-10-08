package com.simp.service.shared.domain.model;

import com.simp.service.shared.domain.security.UserRole;

import java.util.List;

public record Caller(
        String token,
        Account account,
        List<UserRole> roles
) {
}
