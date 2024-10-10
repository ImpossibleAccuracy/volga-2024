package com.simp.service.shared.config.database;

import com.simp.service.shared.domain.model.Account;
import com.simp.service.shared.domain.model.Authorization;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

@Configuration
@EnableR2dbcAuditing
public class DatabaseConfig {
    @Bean
    @ConditionalOnMissingBean
    ReactiveAuditorAware<Long> auditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Authorization.class::cast)
                .map(Authorization::account)
                .map(Account::id);
    }
}
