package com.simp.service.hospital.service;

import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.domain.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class HospitalServiceImpl implements HospitalService {
    @NotNull
    private final AuthService authService;

    @NotNull
    public String getGreeting(@NotNull String name) {
        if (name.startsWith("auth-")) {
            return authService.getGreeting(name.substring(5));
        } else {
            return "Hello, %s [from hospital]".formatted(name);
        }
    }
}
