package com.example.spring_school.controller;

import com.example.spring_school.dto.RestErrorDto;
import com.example.spring_school.exception.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultExceptionHandler {
    private static Logger logger = LogManager.getLogger(ClassController.class);

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<RestErrorDto> defaultExceptionHandler(HttpServletRequest req, BusinessException e) {
        logger.error("Error code = {}, {}", e.getCode(), e.getMessage());
        RestErrorDto errorDto = new RestErrorDto(e.getCode(), e.getMessage(), null);
        return new ResponseEntity<>(errorDto, HttpStatus.OK);
    }
}
