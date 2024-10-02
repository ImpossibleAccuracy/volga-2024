package com.simp.service.shared.server.payload.request;

public record AccountUpdateRequest(
        String lastName,
        String firstName,
        String password
) {
}
