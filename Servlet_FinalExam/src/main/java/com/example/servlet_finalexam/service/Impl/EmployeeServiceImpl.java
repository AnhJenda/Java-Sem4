package com.example.servlet_finalexam.service.Impl;

import com.example.servlet_finalexam.dto.employeeDto;
import com.example.servlet_finalexam.entity.Employee;
import com.example.servlet_finalexam.mapper.EmployeeMapper;
import com.example.servlet_finalexam.mapper.EmployeeMapperImpl;
import com.example.servlet_finalexam.repository.Impl.EmployeeRepositoryImpl;
import com.example.servlet_finalexam.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl();
    EmployeeMapper mapper = new EmployeeMapperImpl();
    @Override
    public List<employeeDto> getListProduct(){
        return employeeRepository.gets().stream().map(p -> mapper.entityToDto(p)).collect(Collectors.toList());
    }
    @Override
    public void createEmployee(employeeDto employeeDto){
        Employee employee = mapper.dtoToEntity(employeeDto);
        employeeRepository.saveEmployee(employee);
    }
    @Override
    public void updateEmployee(int id, employeeDto employeeDto){
        Employee employee = mapper.dtoToEntity(employeeDto);
        employeeRepository.updateEmployee(id, employee);
    }
    @Override
    public employeeDto getEmployeeById(int id){
        Employee employee = employeeRepository.getById(id);
        return mapper.entityToDto(employee);
    }
    @Override
    public  void deleteEmployee(int id){
        employeeRepository.deleteEmployee(id);
    }
}
