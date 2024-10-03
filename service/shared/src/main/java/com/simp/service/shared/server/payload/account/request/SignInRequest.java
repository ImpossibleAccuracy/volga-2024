package com.simp.service.shared.server.payload.account.request;

public record SignInRequest(
        String username,
        String password
) {
}
