package com.simp.service.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication(scanBasePackages = {"com.simp"})
@ConfigurationPropertiesScan("com.simp")
@EnableWebFlux
@EnableFeignClients(basePackages = {"com.simp"})
@EnableReactiveFeignClients(basePackages = {"com.simp"})
public class HospitalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalServiceApplication.class, args);
    }
}
