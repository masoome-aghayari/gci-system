package com.gci.workshop.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GciWorkshopAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(GciWorkshopAgentApplication.class, args);
    }

}
