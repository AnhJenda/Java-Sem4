package com.example.spring_school_client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassDto extends PageDto<PageDto> {
    private long id;
    private String name;
    private String code;
    private Date startTime;
    private Date endTime;
    private int currentSemester;
    private int size;
}
