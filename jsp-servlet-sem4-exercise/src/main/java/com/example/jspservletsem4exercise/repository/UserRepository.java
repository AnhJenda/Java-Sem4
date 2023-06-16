package com.example.jspservletsem4exercise.repository;

import com.example.jspservletsem4exercise.entity.User;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
public interface UserRepository {
    User getByEmail(String email);
}
