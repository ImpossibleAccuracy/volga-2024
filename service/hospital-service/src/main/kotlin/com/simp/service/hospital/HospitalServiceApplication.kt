package com.simp.service.hospital

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients(basePackages = ["com.simp"])
@SpringBootApplication(scanBasePackages = ["com.simp"])
@ConfigurationPropertiesScan("com.simp")
class HospitalServiceApplication

fun main(args: Array<String>) {
    runApplication<HospitalServiceApplication>(*args)
}
