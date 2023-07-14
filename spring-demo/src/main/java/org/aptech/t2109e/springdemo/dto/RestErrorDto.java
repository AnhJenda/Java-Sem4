package org.aptech.t2109e.springdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/*
    @author: Dinh Quang Anh
    Date   : 7/12/2023
    Project: spring-demo
*/
@Getter
@Setter
public class RestErrorDto {
    String errorCode;
    String message;
    Object data;

    public RestErrorDto(String errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
}
