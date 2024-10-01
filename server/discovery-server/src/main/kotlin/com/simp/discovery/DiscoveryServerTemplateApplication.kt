package com.simp.discovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryServerTemplateApplication

fun main(args: Array<String>) {
    runApplication<DiscoveryServerTemplateApplication>(*args)
}
