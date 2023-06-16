package com.example.jspservletsem4exercise.service.impl;

import com.example.jspservletsem4exercise.mapper.UserMapperImpl;
import com.example.jspservletsem4exercise.service.UserService;
import com.example.jspservletsem4exercise.repository.UserRepository;
import com.example.jspservletsem4exercise.repository.impl.UserRepositoryImpl;
import com.example.jspservletsem4exercise.dto.userDto;
import com.example.jspservletsem4exercise.jpa.exception.UserNotFoundException;
import com.example.jspservletsem4exercise.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepositoryImpl();
    UserMapper mapper = new UserMapperImpl();

    @Override
    public Boolean authenUser(String email, String password) {
        userDto user = mapper.entityToDto(userRepository.getByEmail(email));

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }

        String userEmail = user.getEmail();
        if (userEmail != null && StringUtils.isNotEmpty(userEmail) && userEmail.trim().equals(email)
                && user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}
