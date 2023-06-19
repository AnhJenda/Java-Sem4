package com.example.servlet_finalexam.mapper;

import com.example.servlet_finalexam.dto.employeeDto;
import com.example.servlet_finalexam.entity.Employee;
import org.mapstruct.Mapper;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
@Mapper
public interface EmployeeMapper {
    Employee dtoToEntity (employeeDto employeeDto);

    employeeDto entityToDto (Employee employee);
}
