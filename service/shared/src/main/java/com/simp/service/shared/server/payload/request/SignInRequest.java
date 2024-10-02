package com.simp.service.shared.server.payload.request;

public record SignInRequest(
        String username,
        String password
) {
}
