package com.simp.service.timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableFeignClients(basePackages = {"com.simp"})
@SpringBootApplication(scanBasePackages = {"com.simp"})
@ConfigurationPropertiesScan("com.simp")
@EnableWebFlux
@EnableReactiveFeignClients(basePackages = {"com.simp"})
public class TimetableServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimetableServiceApplication.class, args);
    }
}
