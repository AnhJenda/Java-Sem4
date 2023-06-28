package org.aptech.t2109e.springdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
    @author: Dinh Quang Anh
    Date   : 6/28/2023
    Project: spring-demo
*/
@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto
{
    private Integer pageSize;
    private Integer pageNumber;
    /* int != Integer
        Integer là object => có thể null
        int là primative => ko nhận null (kiểu nguyên thủy) - dùng sẽ tối ưu hóa bộ nhớ hơn nhưng dùng khi chắc chắn nó not null
     */
}
