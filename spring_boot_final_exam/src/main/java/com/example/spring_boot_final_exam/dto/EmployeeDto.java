package com.example.spring_boot_final_exam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeDto extends PageDto<PageDto>{
    private long id;
    private String name;
    private BigDecimal wage;
}
