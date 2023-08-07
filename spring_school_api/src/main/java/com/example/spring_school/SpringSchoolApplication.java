package com.example.spring_school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSchoolApplication.class, args);
    }

}
