package com.simp.service.shared.server.payload.dto;

import com.simp.service.shared.domain.model.Account;

public record AccountDto(long id, String firstName, String lastName, String username) implements Account {
}
