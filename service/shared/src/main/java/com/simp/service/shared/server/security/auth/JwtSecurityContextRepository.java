package com.simp.service.shared.server.security.auth;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.domain.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtSecurityContextRepository implements ServerSecurityContextRepository {
    private final ReactiveAuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        var headers = exchange.getRequest().getHeaders();

        var token = headers.getFirst(AuthConstants.AUTH_HEADER);
        if (token == null) {
            return Mono.empty();
        }

        var serviceUser = headers.getFirst(AuthConstants.SERVICE_ROLE_USERNAME);
        var servicePassword = headers.getFirst(AuthConstants.SERVICE_ROLE_PASSWORD);
        var hasServiceRole = checkServiceAuth(serviceUser, servicePassword);

        return getSecurityContext(token, hasServiceRole);
    }

    private Boolean checkServiceAuth(String user, String password) {
        return securityProperties.serviceCredentials.stream()
                .anyMatch(cred -> cred.name().equals(user) &&
                        cred.password().equals(password));
    }

    private Mono<SecurityContext> getSecurityContext(String token, boolean addServerRole) {
        Authentication auth = new UsernamePasswordAuthenticationToken(token, token);

        return authenticationManager
                .authenticate(auth)
                .switchIfEmpty(buildServiceRoleAuthentication(addServerRole))
                .map(a -> {
                    ArrayList<GrantedAuthority> authorities = new ArrayList<>(a.getAuthorities());

                    if (addServerRole) {
                        authorities.add(new SimpleGrantedAuthority(AuthConstants.SERVICE_ROLE));
                    }

                    return new UsernamePasswordAuthenticationToken(
                            a.getPrincipal(),
                            a.getCredentials(),
                            authorities);
                })
                .map(SecurityContextImpl::new);
    }

    private Mono<UsernamePasswordAuthenticationToken> buildServiceRoleAuthentication(boolean addServerRole) {
        if (!addServerRole) return Mono.empty();

        return Mono.just(new UsernamePasswordAuthenticationToken(
                null, null,
                List.of(new SimpleGrantedAuthority(AuthConstants.SERVICE_ROLE))
        ));
    }
}
