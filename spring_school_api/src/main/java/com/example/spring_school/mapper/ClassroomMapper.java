package com.example.spring_school.mapper;
import com.example.spring_school.dto.*;
import com.example.spring_school.entity.Class;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    Class dtoToEntityClass(ClassDto classDto);
    ClassDto entityToDtoClass(Class c);
}
