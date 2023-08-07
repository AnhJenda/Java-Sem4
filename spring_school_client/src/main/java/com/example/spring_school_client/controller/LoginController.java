package com.example.spring_school_client.controller;

import com.example.spring_school_client.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/*
    @author: Dinh Quang Anh
    Date   : 8/3/2023
    Project: Spring_school_client
*/
@Controller

public class LoginController {
    @Autowired
    LoginService loginService;

//    @GetMapping("/login")
//    public ModelAndView
}
