package com.example.spring_boot_final_exam.mapper;

import com.example.spring_boot_final_exam.dto.EmployeeDto;
import com.example.spring_boot_final_exam.entity.Employee;
import org.mapstruct.Mapper;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee DtoToEntity (EmployeeDto employeeDto);
    EmployeeDto EntityToDto (Employee employee);
}
