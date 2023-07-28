package com.example.spring_school.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
    @author: Dinh Quang Anh
    Date   : 7/28/2023
    Project: spring_school
*/
@Configuration
@Data
// @ConfigurationProperties  // auto access
public class ServiceProperties {
    @Value("${service.api.base-url}")
    private String baseUrl;
    @Value("${service.api.get-all-product}")
    private String productListUrl;
}
