package com.simp.service.timetable.server.security;

import com.simp.service.shared.domain.properties.SecurityProperties;
import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.server.security.BaseSecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final SecurityProperties securityProperties;

    @Bean
    SecurityWebFilterChain filterChain(AuthService authService, ServerHttpSecurity http) {
        return BaseSecurityConfiguration.applySecurityFilter(authService, http, securityProperties)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }
}
