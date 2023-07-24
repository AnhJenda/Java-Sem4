package com.example.spring.controller;

import com.example.spring.dto.LoginResponseDto;
import com.example.spring.entity.User;
import com.example.spring.service.UserService;
import com.example.spring.utils.JWTUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
    @author: Dinh Quang Anh
    Date   : 7/21/2023
    Project: spring
*/
@RestController
@RequestMapping(value = "api/v1")
public class LoginController extends BaseController {
    private static Logger logger = LogManager.getLogger(ProductController.class);
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody User user){
        logger.info("process = login, username = {}", user.getUsername());
        // xử lý mất 5s thì log ok; nếu 5s -> 10s thì log: warning chậm, hơn 10s thì cảnh báo nặng hơn.
        long start_time = System.currentTimeMillis();
        LoginResponseDto result = null;
        HttpStatus status = null;
        try{
            if (userService.checkLogin(user)){
                result = JWTUtils.genToken(user);
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
        }
        catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logKPI(start_time, "login");
        return new ResponseEntity<LoginResponseDto>(result, status);
    }
}
