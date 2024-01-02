package com.gci.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GciAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GciAuthenticationApplication.class, args);
    }

}
