package com.example.spring.controller;

import com.example.spring.properties.CommonProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    private static Logger logger = LogManager.getLogger(BaseController.class);
    @Autowired
    protected CommonProperties commonProperties;

    private long level1 = 5000;
    private long level2 = 10000;

    protected void logKPI(long startTime, String method){
        Long processTime = System.currentTimeMillis() - startTime;
        if (processTime < level1){
            logger.info("process time method {} = {}", method, processTime);
        }
        if (level1 <= processTime && processTime <= level2){
            logger.warn("process time method {} = {}", method, processTime);
        }
        if (level2 < processTime){
            logger.warn(".......");
        }
    }
}
