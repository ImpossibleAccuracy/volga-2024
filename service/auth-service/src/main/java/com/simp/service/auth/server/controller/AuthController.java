package com.simp.service.auth.server.controller;

import com.simp.service.shared.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
@RequiredArgsConstructor
public final class AuthController {
    private final AuthService authService;
}
