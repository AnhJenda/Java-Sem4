package com.example.spring_security_jwtyoutube.repository;

import com.example.spring_security_jwtyoutube.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 8/2/2023
    Project: Spring_security_jwtyoutube
*/
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor {

    Optional<User> findByEmail(String username);
}
