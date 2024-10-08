package com.simp.service.shared.domain.model;

import com.simp.service.shared.domain.security.UserRole;

import java.util.List;

public interface Authorization {
    Account account();

    List<UserRole> roles();
}
