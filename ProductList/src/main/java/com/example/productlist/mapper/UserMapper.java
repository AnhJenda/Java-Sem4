package com.example.productlist.mapper;

import com.example.productlist.dto.userDto;
import com.example.productlist.entity.User;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public interface UserMapper {
    User dtoToEntity(userDto userDto);
    userDto entityToDto(User user);
}
