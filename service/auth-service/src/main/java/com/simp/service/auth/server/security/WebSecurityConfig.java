package com.simp.service.auth.server.security;

import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.server.security.BaseSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
    @Bean
    SecurityWebFilterChain filterChain(AuthService authService, ServerHttpSecurity http) {
        return BaseSecurityConfiguration.applySecurityFilter(authService, http)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }
}
