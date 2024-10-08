package com.simp.service.shared.server.security;

import com.simp.service.shared.domain.properties.SecurityProperties;
import com.simp.service.shared.domain.service.AuthService;
import com.simp.service.shared.server.security.auth.JwtAuthenticationManager;
import com.simp.service.shared.server.security.auth.JwtSecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BaseSecurityConfiguration {
    public static ServerHttpSecurity applySecurityFilter(
            AuthService authService,
            ServerHttpSecurity http,
            SecurityProperties securityProperties
    ) {
        var manager = new JwtAuthenticationManager(authService);
        var repo = new JwtSecurityContextRepository(manager, securityProperties);

        return http
                .authenticationManager(manager)
                .securityContextRepository(repo);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
