package com.simp.service.auth.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.simp.service.auth.domain.properties.TokenProperties;
import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenProperties tokenProperties;

    public Mono<Long> getTokenData(String token) {
        return Mono.fromCallable(() -> {
            try {
                var jwt = JWT.decode(token);

                if (!jwt.getIssuer().equals(tokenProperties.issuer) ||
                        !jwt.getAudience().contains(tokenProperties.audience)) return null;

                if (jwt.getExpiresAt().before(new Date())) return null;

                var claim = jwt.getClaim(tokenProperties.claimName);

                return claim.asLong();
            } catch (JWTDecodeException e) {
                return null;
            }
        });
    }

    public String generateToken(Account account) {
        return JWT.create()
                .withAudience(tokenProperties.audience)
                .withIssuer(tokenProperties.issuer)
                .withClaim(tokenProperties.claimName, account.id())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenProperties.ttl))
                .sign(Algorithm.HMAC256(tokenProperties.secret));
    }

    @Override
    public Mono<Boolean> validateToken(String token) {
        return getTokenData(token).map(Objects::nonNull);
    }
}
