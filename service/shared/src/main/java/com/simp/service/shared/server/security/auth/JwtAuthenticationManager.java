package com.simp.service.shared.server.security.auth;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Lazy
@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    private final AuthService authService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        var jwtToken = authentication.getCredentials().toString();

        return authService
                .authUser(jwtToken)
                .map(auth -> getAuthorities(auth.account(), auth.roles()));
    }

    private UsernamePasswordAuthenticationToken getAuthorities(Account account, List<String> roles) {
        return new UsernamePasswordAuthenticationToken(
                account, null,
                roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }
}
