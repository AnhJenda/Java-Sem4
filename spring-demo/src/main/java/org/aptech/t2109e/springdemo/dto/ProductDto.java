package org.aptech.t2109e.springdemo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductDto extends PageDto {
    private long id;
    private String name;
    private long price;
    private LocalDateTime insert_time;
    private LocalDateTime update_time;
    private String createBy;
    private String updateBy;
}
