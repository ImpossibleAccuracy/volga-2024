package com.simp.service.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients(basePackages = ["com.simp"])
@SpringBootApplication(scanBasePackages = ["com.simp"])
@ConfigurationPropertiesScan("com.simp")
class AuthServiceApplication

fun main(args: Array<String>) {
    runApplication<AuthServiceApplication>(*args)
}
