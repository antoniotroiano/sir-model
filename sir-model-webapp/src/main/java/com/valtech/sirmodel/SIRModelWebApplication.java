package com.valtech.sirmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SIRModelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SIRModelWebApplication.class, args);
    }
}
