package com.example.spring_school_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringSchoolClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSchoolClientApplication.class, args);
    }

}
