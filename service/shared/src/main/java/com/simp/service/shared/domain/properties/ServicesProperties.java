package com.simp.service.shared.domain.properties;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "services")
public class ServicesProperties {
    public final String auth;
}
