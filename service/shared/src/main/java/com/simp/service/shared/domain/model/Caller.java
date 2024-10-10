package com.simp.service.shared.domain.model;

import com.simp.service.shared.domain.exception.OperationDeniedException;
import com.simp.service.shared.domain.security.UserRole;

import java.util.List;

public record Caller(
        String token,
        Account account,
        List<UserRole> roles
) {
    public boolean hasRole(UserRole... roles) {
        var rolesList = List.of(roles);

        return roles()
                .stream()
                .anyMatch((r) -> rolesList
                        .stream()
                        .anyMatch(r::include));
    }

    public void requireRole(UserRole... role) {
        if (!hasRole(role)) {
            throw new OperationDeniedException("No such role");
        }
    }
}
