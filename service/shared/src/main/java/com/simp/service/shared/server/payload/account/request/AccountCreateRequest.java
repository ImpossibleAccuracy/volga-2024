package com.simp.service.shared.server.payload.account.request;

import java.util.List;

public record AccountCreateRequest(
        String lastName,
        String firstName,
        String username,
        String password,
        List<String> roles
) {
}
