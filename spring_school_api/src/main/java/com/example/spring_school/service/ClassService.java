package com.example.spring_school.service;

import com.example.spring_school.dto.ClassDto;
import com.example.spring_school.entity.Class;

import java.util.List;

public interface ClassService {
    List<ClassDto> gets(ClassDto criteria);
    ClassDto getById(Long id);
    ClassDto save(ClassDto classDto);
    void delete(Long id);
}
