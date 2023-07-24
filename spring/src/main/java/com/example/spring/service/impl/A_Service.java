package com.example.spring.service.impl;

import com.example.spring.constant.ErrorCode;
import com.example.spring.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/*
    @author: Dinh Quang Anh
    Date   : 7/19/2023
    Project: spring
*/
public abstract class A_Service {

    void validateText(String ...texts){
        Arrays.asList(texts).stream().forEach(item -> validateText(item));
    }

    void  validateText(String text){
        validateText(text, true, null, null);
    }

    void  validateText(String text,boolean mandatory, Integer minLength, Integer maxLength){
        if (mandatory && StringUtils.isEmpty(text)){
            throw new  BusinessException(ErrorCode.PARAM_INVALID.code, "param invalid");
        }
        // todo validate
    }
}
