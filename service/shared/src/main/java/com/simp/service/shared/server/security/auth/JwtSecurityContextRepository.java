package com.simp.service.shared.server.security.auth;

import com.simp.service.shared.contants.AuthConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtSecurityContextRepository implements ServerSecurityContextRepository {
    private final ReactiveAuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        Mono<String> stringMono = Mono.justOrEmpty(swe.getRequest().getHeaders().getFirst(AuthConstants.AUTH_HEADER));
        return stringMono.flatMap(this::getSecurityContext);
    }

    private Mono<? extends SecurityContext> getSecurityContext(String token) {
        Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
        return authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
    }
}
