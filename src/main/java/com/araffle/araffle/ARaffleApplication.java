package com.araffle.araffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ARaffleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ARaffleApplication.class, args);
    }

}
