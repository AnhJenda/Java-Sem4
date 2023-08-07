package com.example.examapi.service;

import com.example.examapi.dto.LoginResponseDto;
import com.example.examapi.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(long id);
    boolean add(User user);
    User getByName(String username);
    boolean checkLogin(User user);

    LoginResponseDto returnLogin(User user);
    void sendMessage(String message);
}
