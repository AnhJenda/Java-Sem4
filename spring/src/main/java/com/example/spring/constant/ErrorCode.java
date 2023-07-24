package com.example.spring.constant;

/*
    @author: Dinh Quang Anh
    Date   : 7/19/2023
    Project: spring
*/
public enum ErrorCode {
    PARAM_INVALID("201");
    public String code;
    ErrorCode(String code) {
        this.code = code;
    }
}