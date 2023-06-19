package com.example.servlet_finalexam.mapper;

import com.example.servlet_finalexam.dto.employeeDto;
import com.example.servlet_finalexam.entity.Employee;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-19T20:32:47+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee dtoToEntity(employeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setFullname( employeeDto.getFullname() );
        employee.setBirthday( employeeDto.getBirthday() );
        employee.setAddress( employeeDto.getAddress() );
        employee.setPosition( employeeDto.getPosition() );
        employee.setDepartment( employeeDto.getDepartment() );

        return employee;
    }

    @Override
    public employeeDto entityToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        employeeDto employeeDto = new employeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setFullname( employee.getFullname() );
        employeeDto.setBirthday( employee.getBirthday() );
        employeeDto.setAddress( employee.getAddress() );
        employeeDto.setPosition( employee.getPosition() );
        employeeDto.setDepartment( employee.getDepartment() );

        return employeeDto;
    }
}
