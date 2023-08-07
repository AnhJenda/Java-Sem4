package com.example.spring_school.controller;

import com.example.spring_school.dto.ClassDto;
import com.example.spring_school.entity.Class;
import com.example.spring_school.entity.Student;
import com.example.spring_school.exception.BusinessException;
import com.example.spring_school.mapper.StudentMapper;
import com.example.spring_school.repo.StudentRepository;
import com.example.spring_school.service.ClassService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
public class ClassController extends BaseController {
    private static Logger logger = LogManager.getLogger(ClassController.class);

    @Autowired
    private ClassService service;
    @Autowired
    private StudentRepository repository;
    @Autowired
    private StudentMapper mapper;

    @PostMapping(value = "/classrooms")
    public ResponseEntity<?> gets(@RequestBody ClassDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<ClassDto> classes = service.gets(criteria);

        System.out.println(classes);

        if (Objects.isNull(classes) || classes.size() <= 0) {
            throw new BusinessException("202", "No data");
        }

        return ResponseEntity.ok(classes);
    }

    @GetMapping(value = "/classroom")
    public ResponseEntity<?> get(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            throw new BusinessException("202", "No data");
        } else {
            ClassDto c = service.getById(id);
            List<Student> students = repository.getStudentsByClasses(c.getId());
            c.setStudentDtoList(students.stream().map(mapper::entityToDtoClass).collect(Collectors.toList()));
            return ResponseEntity.ok(c);
        }
    }

    @PostMapping(value = "/classroom/save")
    public ResponseEntity<?> save(@RequestBody ClassDto classDto, HttpServletRequest request) {
        ClassDto cl = service.save(classDto);
        if (Objects.isNull(cl)) {
            throw new BusinessException("202", "No data");
        } else {
            return ResponseEntity.ok(cl);
        }
    }

    @PostMapping(value = "/classroom/delete")
    public ResponseEntity<?> delete(@RequestParam(required = false) Long id){
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
