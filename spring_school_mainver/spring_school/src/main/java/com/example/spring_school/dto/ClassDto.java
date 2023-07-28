package com.example.spring_school.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassDto extends PageDto {
    private int id;
    private String name;
    private String code;
    private Date startTime;
    private Date endTime;
    private int currentSemester;
    private int size;
}
