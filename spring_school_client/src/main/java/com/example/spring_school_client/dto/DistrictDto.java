package com.example.spring_school_client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/*
    @author: Dinh Quang Anh
    Date   : 8/2/2023
    Project: Spring_school_client
*/
@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictDto {
    private long id;
    private String name;
    private String value;
    private Date provinceId;
}
