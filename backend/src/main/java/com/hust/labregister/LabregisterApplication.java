package com.hust.labregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LabregisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabregisterApplication.class, args);
    }

}
