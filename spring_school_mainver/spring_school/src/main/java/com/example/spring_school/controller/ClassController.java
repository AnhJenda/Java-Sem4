package com.example.spring_school.controller;

import com.example.spring_school.dto.ClassDto;
import com.example.spring_school.exception.BusinessException;
import com.example.spring_school.service.ClassService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1")
public class ClassController extends BaseController {
    private static Logger logger = LogManager.getLogger(ClassController.class);

    @Autowired
    private ClassService service;

    @PostMapping(value = "/classes")
    public ResponseEntity<?> gets(@RequestBody ClassDto criteria, HttpServletRequest request) {
//        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
//            criteria.setPageSize(commonProperties.getPageSize());
//        }
//        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
//            criteria.setPageNumber(commonProperties.getPageNumber());
//        }
        List<ClassDto> classes = service.gets(criteria);

        if (Objects.isNull(classes) || classes.size() <= 0) {
            throw new BusinessException("202", "No data");
        }

        return ResponseEntity.ok(classes);
    }

    @GetMapping(value = "/class")
    public ResponseEntity<?> get(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            throw new BusinessException("202", "No data");
        } else {
            return ResponseEntity.ok(service.getById(id));
        }
    }

    @PostMapping(value = "/class/save")
    public ResponseEntity<?> save(@RequestBody ClassDto classDto, HttpServletRequest request) {
        ClassDto cl = service.save(classDto);
        if (Objects.isNull(cl)) {
            throw new BusinessException("202", "No data");
        } else {
            return ResponseEntity.ok(cl);
        }
    }


}
