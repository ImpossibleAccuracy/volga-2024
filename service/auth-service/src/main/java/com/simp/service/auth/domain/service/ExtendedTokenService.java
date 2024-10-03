package com.simp.service.auth.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.simp.service.auth.domain.properties.TokenProperties;
import com.simp.service.shared.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExtendedTokenService {
    private final TokenProperties tokenProperties;

    public Long getTokenUser(String token) {
        var jwt = JWT.decode(token);

        if (!jwt.getIssuer().equals(tokenProperties.issuer) ||
                !jwt.getAudience().contains(tokenProperties.audience)) return null;

        if (jwt.getExpiresAt().before(new Date())) return null;

        var claim = jwt.getClaim(tokenProperties.claimName);

        return claim.asLong();
    }

    public String generateToken(Account account) {
        return JWT.create()
                .withAudience(tokenProperties.audience)
                .withIssuer(tokenProperties.issuer)
                .withClaim(tokenProperties.claimName, account.id())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenProperties.ttl))
                .sign(Algorithm.HMAC256(tokenProperties.secret));
    }
}
