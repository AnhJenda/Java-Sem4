package com.example.spring_boot_final_exam.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/*
    @author: Dinh Quang Anh
    Date   : 6/28/2023
    Project: spring-demo
*/
@Configuration
@Data
public class CommonProperties {
    @Value("${application.common.default-page-size}")
    private Integer pageSize;
    @Value("${application.common.default-page-number}")
    private Integer pageNumber;
}
//@ConfigurationProperties(value = "application.common")
//@Data
//public class CommonProperties {
//    private Integer defaultPageSize;
//    @Value("application.default-page-number")
//    private Integer defaultPageNumber;
//}
