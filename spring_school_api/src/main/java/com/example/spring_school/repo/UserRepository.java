package com.example.spring_school.repo;

import com.example.spring_school.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 8/6/2023
    Project: spring_school_api
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
