package com.david.raspberrypi.irrigation.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.david.raspberrypi.irrigation"})
@EnableJpaRepositories(basePackages = "com.david.raspberrypi.irrigation.persistence")
@EntityScan(basePackages = "com.david.raspberrypi.irrigation.persistence")
public class SpringBootIrrigationInitializer extends SpringBootServletInitializer {
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootIrrigationInitializer.class, args);
    }
}
