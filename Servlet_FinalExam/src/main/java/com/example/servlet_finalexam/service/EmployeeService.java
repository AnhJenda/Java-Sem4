package com.example.servlet_finalexam.service;

import com.example.servlet_finalexam.dto.employeeDto;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
public interface EmployeeService {
    List<employeeDto> getListProduct();
    void createEmployee(employeeDto employeeDto);
}
