package com.example.spring_school.service.impl;

import com.example.spring_school.config.ServiceProperties;
import com.example.spring_school.dto.LoginResponseDto;
import com.example.spring_school.entity.User;
import com.example.spring_school.repo.UserRepository;
import com.example.spring_school.service.UserService;
import com.example.spring_school.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl extends A_Service implements UserService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ServiceProperties serviceProperties;

    @Autowired
    UserRepository repository;

    List<User> users;


    private void test1(){
        String url =  serviceProperties.getBaseUrl() + serviceProperties.getProductListUrl();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json"); // set header
        httpHeaders.add("Authorization", "Bearer + token"); // set token
        User user = new User();

        user.setUsername("demo");
        user.setPassword("demo");

        HttpEntity entity = new  HttpEntity<>(user, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity, String.class);
        System.out.println(response.getBody());
    }



//    static {
//        User user1 = new User(1, "admin1", "admin@123", "admin1@gmail.com", "234234234");
//        user1.setRoles(new String[]{"ROLE_ADMIN"});
//
//        User user2 = new User(2, "admin2", "admin@123", "admin2@gmail.com", "222222222");
//        user2.setRoles(new String[]{"ROLE_USER"});
//
//        User user3 = new User(3, "admin3", "admin@123", "admin3@gmail.com", "233333333");
//        user3.setRoles(new String[]{"ROLE_ADMIN", "ROLE_USER"});
//
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//    }

    @Override
    public LoginResponseDto returnLogin(User user) {
        return LoginResponseDto.builder()
                .access_token(JWTUtils.genToken(user))
                .refresh_token(JWTUtils.genRefreshToken(user))
                .timeToLive(System.currentTimeMillis() + JWTUtils.expireTime)
                .build();
    }

    @Override
    public List<User> findAll() {
        users = repository.findAll();
        return users;
    }

    @Override
    public User findById(long id) {
//        return users.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
        if (id > 0) {
           return repository.getById(id);
        }

        return null;
    }

    @Override
    public boolean add(User user) {
        if (!Objects.isNull(user) && user.getId() == 0) {
            validateText(user.getUsername(), user.getPassword(), user.getEmail(), user.getEmail());
            Optional<User> existedUser = users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
            if (existedUser.isPresent()) return false;
            repository.save(user);
//            users.add(user);
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
        for (User existedUser : findAll()) {
            if (user.getUsername().equals(existedUser.getUsername()) && user.getPassword().equals(existedUser.getPassword())) {
                return true;
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        System.err.println(JWTUtils.genToken(users.get(0)));
//
//        JWTUtils jwtUtils = new JWTUtils();
//        String token = JWTUtils.genToken(users.get(0));
//        System.err.println(jwtUtils.getExpireDateFormToken(token));
//    }
}
