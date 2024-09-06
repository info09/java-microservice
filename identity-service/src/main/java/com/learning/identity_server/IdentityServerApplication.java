package com.learning.identity_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IdentityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityServerApplication.class, args);
    }
}
