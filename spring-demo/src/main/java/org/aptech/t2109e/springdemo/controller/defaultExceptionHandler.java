package org.aptech.t2109e.springdemo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aptech.t2109e.springdemo.dto.RestErrorDto;
import org.aptech.t2109e.springdemo.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/*
    @author: Dinh Quang Anh
    Date   : 7/12/2023
    Project: spring-demo
*/
public class defaultExceptionHandler {
    private static Logger logger = LogManager.getLogger(ProductController.class);
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<RestErrorDto> defaultExceptionHandler(HttpServletRequest req, BusinessException e) {
        logger.error("Error : code = {} , {}", e.getCode(), e.getMessage());
        RestErrorDto errorDto = new RestErrorDto(e.getCode(), e.getMessage(), null);
        return new ResponseEntity<>(errorDto, HttpStatus.OK);
    }
}
