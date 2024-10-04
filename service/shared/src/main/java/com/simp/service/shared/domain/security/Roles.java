package com.simp.service.shared.domain.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
public enum Roles {
    ADMIN("ADMIN", 0),
    USER("USER", 0),
    DOCTOR("DOCTOR", 0);

    private final String dbName;
    private final int priority;
}
