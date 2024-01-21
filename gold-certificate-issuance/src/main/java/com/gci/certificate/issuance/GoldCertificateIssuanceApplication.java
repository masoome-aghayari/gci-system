package com.gci.certificate.issuance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GoldCertificateIssuanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldCertificateIssuanceApplication.class, args);
    }

}
