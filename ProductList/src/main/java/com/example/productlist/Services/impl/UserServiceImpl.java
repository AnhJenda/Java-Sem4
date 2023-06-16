package com.example.productlist.Services.impl;

import com.example.productlist.Repositories.UserRepository;
import com.example.productlist.Repositories.iml.UserRepositoryImpl;
import com.example.productlist.Services.UserService;
import com.example.productlist.dto.userDto;
import com.example.productlist.jpa.exception.UserNotFoundException;
import com.example.productlist.mapper.UserMapper;
import com.example.productlist.mapper.impl.UserMapperImpl;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepositoryImpl();

    UserMapper mapper = new UserMapperImpl();

    @Override
    public Boolean authenUser(String username, String password){
        userDto user = mapper.entityToDto(userRepository.getByUserName(username));

        if(user == null){
            throw new UserNotFoundException("");
        }
        return (user.getUsername().trim().equals(username) && user.getPassword().equals(password));
    }
}
