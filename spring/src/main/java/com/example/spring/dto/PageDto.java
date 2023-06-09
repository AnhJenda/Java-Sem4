package com.example.spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto {
    private Integer pageSize;
    private Integer pageNumber;
    /* int () != Integer
    Integer: là 1 object -> có thể null
    int: là primative kiểu dữ liệu nguyên thủy (dùng khi chắc chắn là not null)
    */
}
