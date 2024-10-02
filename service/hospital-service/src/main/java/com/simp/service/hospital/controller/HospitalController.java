package com.simp.service.hospital.controller;

import com.simp.service.shared.domain.payload.GreetingsResponse;
import com.simp.service.shared.domain.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
@RequiredArgsConstructor
public final class HospitalController {
    @NotNull
    private final HospitalService hospitalService;

    @GetMapping
    @NotNull
    public GreetingsResponse getGreetings(@RequestParam("name") @NotNull String name) {
        String greeting = this.hospitalService.getGreeting(name);
        return new GreetingsResponse(greeting);
    }
}
