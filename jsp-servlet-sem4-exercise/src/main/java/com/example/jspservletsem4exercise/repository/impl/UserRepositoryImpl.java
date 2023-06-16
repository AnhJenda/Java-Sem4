package com.example.jspservletsem4exercise.repository.impl;

import com.example.jspservletsem4exercise.entity.User;
import com.example.jspservletsem4exercise.jpa.impl.JpaExecutorImplement;
import com.example.jspservletsem4exercise.repository.UserRepository;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
public class UserRepositoryImpl extends JpaExecutorImplement<User> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User getByEmail(String email){
        return super.getByField("email", email);
    }
}
