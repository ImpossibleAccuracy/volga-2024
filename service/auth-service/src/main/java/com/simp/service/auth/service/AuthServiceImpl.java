package com.simp.service.auth.service;

import com.simp.service.shared.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public String getGreeting(String name) {
        return "Hello, %s [from auth-service]".formatted(name);
    }
}
