package com.example.servlet_finalexam.repository;

import com.example.servlet_finalexam.entity.Employee;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
public interface EmployeeRepository {
    List<Employee> gets();
    void saveEmployee(Employee employee);
    void updateEmployee(int id, Employee employee);
    Employee getEmployeeById(int id);
    void deleteEmployee(int id);
}
