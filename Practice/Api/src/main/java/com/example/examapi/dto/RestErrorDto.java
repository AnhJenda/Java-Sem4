package com.example.examapi.dto;

import lombok.Getter;
import lombok.Setter;

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
