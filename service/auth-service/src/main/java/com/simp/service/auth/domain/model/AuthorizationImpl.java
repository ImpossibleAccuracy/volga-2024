package com.simp.service.auth.domain.model;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Authorization;

import java.util.List;

public record AuthorizationImpl(Account account, List<String> roles) implements Authorization {
}
