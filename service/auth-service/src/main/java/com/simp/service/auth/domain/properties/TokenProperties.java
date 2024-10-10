package com.simp.service.auth.domain.properties;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app.token")
public class TokenProperties {
    public final String secret;
    public final String issuer;
    public final String audience;
    public final long ttl;
}
