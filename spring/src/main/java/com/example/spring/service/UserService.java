package com.example.spring.service;

import com.example.spring.dto.LoginResponseDto;
import com.example.spring.entity.User;

/*
    @author: Dinh Quang Anh
    Date   : 7/19/2023
    Project: spring
*/
public interface UserService {
    User findById(long Id);
    boolean add(User user);
    User getByName(String username);
    boolean checkLogin(User user);
    public LoginResponseDto returnLogin (User user);
}
