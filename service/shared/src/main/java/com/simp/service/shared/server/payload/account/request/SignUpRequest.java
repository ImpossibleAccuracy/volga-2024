package com.simp.service.shared.server.payload.account.request;

public record SignUpRequest(
        String lastName,
        String firstName,
        String username,
        String password
) {
}
