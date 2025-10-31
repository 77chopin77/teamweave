package com.example.teamweave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.teamweave")
public class TeamweaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamweaveApplication.class, args);
    }
}
