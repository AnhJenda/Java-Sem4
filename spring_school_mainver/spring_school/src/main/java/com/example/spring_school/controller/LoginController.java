package com.example.spring_school.controller;

import com.example.spring_school.dto.LoginResponseDto;
import com.example.spring_school.entity.User;
import com.example.spring_school.service.UserService;
import com.example.spring_school.utils.JWTUtils;
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

@RestController
@RequestMapping(value = "api/v1")
public class LoginController extends BaseController{
    private static Logger logger = LogManager.getLogger(ClassController.class);

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(HttpServletRequest request, @RequestBody User user) {
        long startTime = System.currentTimeMillis();
        logger.info("process = login, username = {}", user.getUsername());
        String result = "";
        LoginResponseDto loginResponseDto = null;
        HttpStatus status = null;
        try {
            if (userService.checkLogin(user)) {
                result = JWTUtils.genToken(user);
                status = HttpStatus.OK;
                loginResponseDto = userService.returnLogin(user);
            } else {
                result = "user not valid";
                status = HttpStatus.UNAUTHORIZED;
            }
        }catch (Exception e) {
            result = "user not valid";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        logKpi(startTime, "login");
        return new ResponseEntity<LoginResponseDto>(loginResponseDto, status);
    }
}
