package com.example.spring.controller;

import com.example.spring.properties.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    @Autowired
    protected CommonProperties commonProperties;
}
