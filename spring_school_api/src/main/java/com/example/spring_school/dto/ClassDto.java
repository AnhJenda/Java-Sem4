package com.example.spring_school.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassDto extends PageDto<SpringDataJaxb.PageDto> {
    private long id;
    private String name;
    private String code;
    private Date startTime;
    private Date endTime;
    private int currentSemester;
    private int size;
    List<StudentDto> studentDtoList;
}
