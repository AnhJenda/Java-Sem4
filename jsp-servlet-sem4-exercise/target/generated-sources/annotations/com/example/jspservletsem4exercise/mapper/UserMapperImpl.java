package com.example.jspservletsem4exercise.mapper;

import com.example.jspservletsem4exercise.dto.userDto;
import com.example.jspservletsem4exercise.entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-16T19:27:04+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User dtoToEntity(userDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setName( userDto.getName() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    @Override
    public userDto entityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        userDto userDto = new userDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
