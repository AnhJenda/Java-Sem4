package com.example.jspservletsem4exercise.mapper;

import com.example.jspservletsem4exercise.dto.userDto;
import com.example.jspservletsem4exercise.entity.User;
import org.mapstruct.Mapper;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
@Mapper
public interface UserMapper {
    User dtoToEntity(userDto userDto);
    userDto entityToDto(User user);
}
