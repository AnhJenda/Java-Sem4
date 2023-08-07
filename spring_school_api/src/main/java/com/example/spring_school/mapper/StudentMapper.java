package com.example.spring_school.mapper;

import com.example.spring_school.dto.ClassDto;
import com.example.spring_school.dto.StudentDto;
import com.example.spring_school.entity.Class;
import com.example.spring_school.entity.Student;
import org.mapstruct.Mapper;

/*
    @author: Dinh Quang Anh
    Date   : 8/7/2023
    Project: spring_school_api
*/
@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student dtoToEntityClass(StudentDto studentDto);
    StudentDto entityToDtoClass(Student student);
}
