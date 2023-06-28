package com.example.servlet_finalexam.repository.Impl;

import com.example.servlet_finalexam.entity.Employee;
import com.example.servlet_finalexam.jpa.impl.JpaExecutorImplement;
import com.example.servlet_finalexam.mapper.EmployeeMapper;
import com.example.servlet_finalexam.repository.EmployeeRepository;
import com.example.servlet_finalexam.service.EmployeeService;
import com.example.servlet_finalexam.service.Impl.EmployeeServiceImpl;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
public class EmployeeRepositoryImpl extends JpaExecutorImplement<Employee> implements EmployeeRepository {

    public EmployeeRepositoryImpl() {
        super(Employee.class);
    }

    @Override
    public List<Employee> gets() {
        return super.findall();
    }

    @Override
    public void saveEmployee(Employee employee){
        super.createNewRecord(employee);
    }
    @Override
    public void updateEmployee(int id, Employee employee){
        super.updateRecord(id, employee);
    }
    @Override
    public Employee getEmployeeById(int id){
        return super.getById(id);
    }
    @Override
    public void deleteEmployee(int id){
        super.deleteRecord(id);
    }
}
