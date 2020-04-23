package com.nono.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrederFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrederFeignMain80.class, args);
    }
}
