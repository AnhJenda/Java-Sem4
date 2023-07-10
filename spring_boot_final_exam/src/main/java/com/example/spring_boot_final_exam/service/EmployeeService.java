package com.example.spring_boot_final_exam.service;

import com.example.spring_boot_final_exam.dto.EmployeeDto;
import com.example.spring_boot_final_exam.dto.PageDto;
import com.example.spring_boot_final_exam.entity.Employee;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
public interface EmployeeService {
    PageDto<EmployeeDto> getAll(EmployeeDto criteria);

    Employee create(EmployeeDto employeeDto);

    PageDto<EmployeeDto> getAllByName(String name, EmployeeDto criteria);


//    Employee update(Long id, EmployeeDto employeeDto);
//
//    void delete (Long id);
//    EmployeeDto getById(long id);
    EmployeeDto save (EmployeeDto employeeDto);
}
