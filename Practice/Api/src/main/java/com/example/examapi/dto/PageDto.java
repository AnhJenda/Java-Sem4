package com.example.examapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto<P> {
    private Integer pageSize;
    private Integer pageNumber;
    /* int () != Integer
    Integer: là 1 object -> có thể null
    int: là primative kiểu dữ liệu nguyên thủy (dùng khi chắc chắn là not null)
    */
}
