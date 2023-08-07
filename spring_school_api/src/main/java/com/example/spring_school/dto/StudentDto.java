package com.example.spring_school.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    private long id;
    private String name;
    private String code;
    private String birthDate;
    private int gender;
    private String address;
    private String tel;
    List<ClassDto> classDtoList;
}


