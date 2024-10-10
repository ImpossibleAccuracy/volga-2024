package com.simp.service.shared.domain.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
public enum UserRole {
    ADMIN,
    MANAGER,
    DOCTOR;

    private List<UserRole> childRoles = new ArrayList<>();

    public boolean include(UserRole other) {
        return this == other || childRoles.stream().anyMatch(r -> r.include(other));
    }

    static {
        ADMIN.childRoles = List.of(UserRole.MANAGER);
    }
}
