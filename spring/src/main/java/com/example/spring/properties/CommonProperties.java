package com.example.spring.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration  // Ăn cấu hình config
@Data // không cần viết get set
public class CommonProperties {
    @Value("${application.common.default-page-size}")
    private Integer pageSize;
    @Value("${application.common.default-page-number}")
    private Integer pageNumber;
}


//C1
//@Data // không cần viết get set
//@ConfigurationProperties(value = "default-page-size")  // Ăn cấu hình config
//public class CommonProperties {
//    @Value("application.default-page-size")
//    private Integer pageSize;
//    @Value("application.default-page-number")
//    private Integer pageNumber;
//}
