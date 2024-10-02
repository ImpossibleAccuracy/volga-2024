package com.simp.service.auth.controller;

import com.simp.service.shared.domain.payload.GreetingsResponse;
import com.simp.service.shared.domain.service.AuthService;
import kotlin.jvm.internal.Intrinsics;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
@RequiredArgsConstructor
public final class AuthController {
    @NotNull
    private final AuthService authService;

    @GetMapping
    @NotNull
    public GreetingsResponse getGreetings(@RequestParam("name") @NotNull String name) {
        String greeting = this.authService.getGreeting(name);
        return new GreetingsResponse(greeting);
    }
}
