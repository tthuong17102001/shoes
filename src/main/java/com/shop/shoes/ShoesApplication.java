package com.shop.shoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoesApplication.class, args);
    }

}
