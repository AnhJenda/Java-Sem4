package com.example.spring_school_client.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
}


