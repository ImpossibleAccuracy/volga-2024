package com.simp.service.shared.domain.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
public enum UserRole {
    ADMIN(0),
    USER(0),
    DOCTOR(0);

    private final int priority;
}
