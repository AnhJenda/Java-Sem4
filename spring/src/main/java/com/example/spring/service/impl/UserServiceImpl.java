package com.example.spring.service.impl;

import com.example.spring.dto.LoginResponseDto;
import com.example.spring.entity.User;
import com.example.spring.service.UserService;
import com.example.spring.utils.JWTUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/*
    @author: Dinh Quang Anh
    Date   : 7/19/2023
    Project: spring
*/
@Service
public class UserServiceImpl extends A_Service implements UserService {

    public static List<User> users = new ArrayList<>();
    static {
        User user1 = new User(1, "quang anh", "admin1", "admin@gmail.com", "admin@123",
                "0395100761");
        user1.setRoles(new String[]{"ROLE_ADMIN"});
        User user2 = new User(2, "quang anh 2", "admin2", "admin2@gmail.com", "admin@123",
                "0395100761");
        user2.setRoles(new String[]{"ROLE_USER"});
        User user3 = new User(3, "quang anh3", "admin3", "admin3@gmail.com", "admin@123",
                "0395100761");
        user3.setRoles(new String[]{"ROLE_ADMIN", "ROLE_USER"});

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public static void main(String[] args) {
        JWTUtils jwtUtils = new JWTUtils();
        String token = JWTUtils.genToken(users.get(0));
    }


    @Override
    public User findById(long Id) {
        // cách 1
        return users.stream().filter(item -> item.getId() == Id).findFirst().orElse(null);
        // cách 2: dùng vòng lặp
    }

    @Override
    public boolean add(User user) {
        if (!Objects.isNull(user) && user.getId() == 0){
            validateText(user.getUsername(), user.getPassword(), user.getEmail(), user.getTel());

            Optional<User> existedUser = users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
            if (existedUser.isPresent()) {
                return false;
            }

            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public User getByName(String username) {
        return null;
    }

    @Override
    public boolean checkLogin(User user) {
        for (User existedUser : users){
            if (user.getUsername().equals(existedUser.getUsername()) && user.getPassword().equals(existedUser.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public LoginResponseDto returnLogin (User user){
        return LoginResponseDto.builder().access_token(JWTUtils.genToken(user))
                .refresh_token(JWTUtils.genRefreshToken(user)).timeToLive(System.currentTimeMillis() + JWTUtils.expireTime)
                .build();
    }


}
