package com.simp.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigServerTemplateApplication

fun main(args: Array<String>) {
    runApplication<ConfigServerTemplateApplication>(*args)
}
