package com.simp.service.auth.data.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.simp.service.auth.domain.properties.TokenProperties;
import com.simp.service.auth.domain.service.TokenService;
import com.simp.service.shared.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final static String claimName = "user_id";

    private final TokenProperties tokenProperties;

    @Override
    public Mono<Long> getTokenData(String token) {
        return Mono.fromCallable(() -> {
            try {
                var jwt = JWT.decode(token);

                if (!jwt.getIssuer().equals(tokenProperties.issuer) ||
                        !jwt.getAudience().contains(tokenProperties.audience)) return null;

                if (jwt.getExpiresAt().before(new Date())) return null;

                var claim = jwt.getClaim(claimName);

                return claim.asLong();
            } catch (JWTDecodeException | NullPointerException e) {
                return null;
            }
        });
    }

    @Override
    public String generateToken(Account account) {
        return JWT.create()
                .withAudience(tokenProperties.audience)
                .withIssuer(tokenProperties.issuer)
                .withClaim(claimName, account.id())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenProperties.ttl))
                .sign(Algorithm.HMAC256(tokenProperties.secret));
    }

    @Override
    public Mono<Boolean> validateToken(String token) {
        return getTokenData(token).map(Objects::nonNull);
    }
}
