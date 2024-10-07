package com.simp.service.shared.data.config;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.domain.properties.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactivefeign.client.ReactiveHttpRequestInterceptors;
import reactivefeign.utils.Pair;

import java.util.List;

@Configuration
public class FeignInterceptor {
    @Bean
    public ReactiveHttpRequestInterceptor serviceIdentityInterceptor(SecurityProperties securityProperties) {
        var user = securityProperties.serviceCredentials.stream().findAny().get();

        return ReactiveHttpRequestInterceptors.addHeaders(
                List.of(
                        new Pair<>(AuthConstants.SERVICE_ROLE_USERNAME, user.name()),
                        new Pair<>(AuthConstants.SERVICE_ROLE_PASSWORD, user.password())
                )
        );
    }
}
