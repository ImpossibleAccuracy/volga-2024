package com.simp.service.hospital.server.controller;

import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.server.mapper.dto.Mappers;
import com.simp.service.shared.server.payload.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class HospitalController {
    private final AuthService authService;

    @GetMapping("/sample")
    public Mono<AccountDto> sample() {
        return authService
                .signIn("testuser", "testuserpass")
                .map(Mappers::toDto);
    }
}
