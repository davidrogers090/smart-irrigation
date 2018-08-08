package com.david.raspberrypi.irrigation.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.david.raspberrypi.irrigation"})
public class SpringBootIrrigationInitializer extends SpringBootServletInitializer {
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootIrrigationInitializer.class, args);
    }
}
