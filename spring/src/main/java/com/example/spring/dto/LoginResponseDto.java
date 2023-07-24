package com.example.spring.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/*
    @author: Dinh Quang Anh
    Date   : 7/21/2023
    Project: spring
*/
@Getter
@Setter
@SuperBuilder
public class LoginResponseDto {
    String access_token;
    String refresh_token;
    long timeToLive;
}
