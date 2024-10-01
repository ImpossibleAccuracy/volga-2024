package com.simp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .cors {
                it.disable()
            }
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/actuator/**").permitAll()
                    .anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())
            .build()
}
