package com.simp.service.shared.domain.properties;

import com.simp.service.shared.server.security.model.UserPair;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {
    public final List<UserPair> serviceCredentials;
}
