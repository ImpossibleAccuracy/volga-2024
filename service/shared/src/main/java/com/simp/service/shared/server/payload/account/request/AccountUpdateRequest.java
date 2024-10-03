package com.simp.service.shared.server.payload.account.request;

public record AccountUpdateRequest(
        String lastName,
        String firstName,
        String password
) {
}
