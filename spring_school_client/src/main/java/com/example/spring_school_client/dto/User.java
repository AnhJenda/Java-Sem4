package com.example.spring_school_client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @author: Dinh Quang Anh
    Date   : 7/31/2023
    Project: Spring_school_frontend
*/
@Data
@NoArgsConstructor
public class User {
    private String username;
    private String password;
}
