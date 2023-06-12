package com.example.productlist.mapper.impl;

import com.example.productlist.dto.ProductDto;
import com.example.productlist.dto.userDto;
import com.example.productlist.entity.Product;
import com.example.productlist.entity.User;
import com.example.productlist.mapper.UserMapper;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public class UserMapperImpl implements UserMapper {
    public userDto entityToDto(User p) {
        return new userDto();
    }
    public User dtoToEntity (userDto p){
        return new User();
    }
}
