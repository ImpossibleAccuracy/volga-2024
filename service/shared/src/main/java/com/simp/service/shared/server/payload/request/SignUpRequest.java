package com.simp.service.shared.server.payload.request;

public record SignUpRequest(
        String lastName,
        String firstName,
        String username,
        String password
) {
}
