package com.example.spring_boot_final_exam.repository;

import com.example.spring_boot_final_exam.dto.EmployeeDto;
import com.example.spring_boot_final_exam.dto.PageDto;
import com.example.spring_boot_final_exam.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor {
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
