package com.example.spring_boot_final_exam.service.impl;

import com.example.spring_boot_final_exam.dto.EmployeeDto;
import com.example.spring_boot_final_exam.dto.PageDto;
import com.example.spring_boot_final_exam.entity.Employee;
import com.example.spring_boot_final_exam.mapper.EmployeeMapper;
import com.example.spring_boot_final_exam.repository.EmployeeRepository;
import com.example.spring_boot_final_exam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
@Service
public class EmployeeServiceImplement implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public PageDto<EmployeeDto> getAll(EmployeeDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<EmployeeDto> employeeDtos = employees.getContent()
                .stream()
                .map(mapper::EntityToDto)
                .collect(Collectors.toList());

        PageDto<EmployeeDto> pageDto = new PageDto<>();
        pageDto.setPageSize(employees.getSize());
        pageDto.setPageNumber(employees.getNumber());
        pageDto.setTotalPages(employees.getTotalPages());
        pageDto.setContent(employeeDtos);

        return pageDto;
    }

    public PageDto<EmployeeDto> getAllByName(String name, EmployeeDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Employee> employees = employeeRepository.findByNameContainingIgnoreCase(name, pageable);
        List<EmployeeDto> employeeDtos = employees.getContent()
                .stream()
                .map(mapper::EntityToDto)
                .collect(Collectors.toList());

        PageDto<EmployeeDto> pageDto = new PageDto<>();
        pageDto.setPageSize(employees.getSize());
        pageDto.setPageNumber(employees.getNumber());
        pageDto.setTotalPages(employees.getTotalPages());
        pageDto.setContent(employeeDtos);

        return pageDto;
    }

//    @Override
//    public EmployeeDto getById(long id){
//        return mapper.EntityToDto(employeeRepository.getById(id));
//    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto){
        Employee employee = mapper.DtoToEntity(employeeDto);
        if (Objects.isNull(employee)){
            return null;
        }else {
            Employee returnEntity = employeeRepository.save(employee);
            return mapper.EntityToDto(returnEntity);

        }
    }

    @Override
    public Employee create(EmployeeDto employeeDto){
        Employee employee = mapper.DtoToEntity(employeeDto);
        return employeeRepository.save(employee);
    }


//    @Override
//    public Employee update(Long id, EmployeeDto employeeDto){
//        Employee existingProduct = employeeRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
//        Employee employee = mapper.DtoToEntity(employeeDto);
//        return employeeRepository.save(employee);
//    }
//
//    @Override
//    public void delete(Long id){
//        Employee existingProduct = employeeRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
//        employeeRepository.deleteById(id);
//    }
}
