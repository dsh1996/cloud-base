package com.server.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AuthServerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(AuthServerApplication.class, args);
    }

}
