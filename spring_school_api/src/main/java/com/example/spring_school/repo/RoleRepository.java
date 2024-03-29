package com.example.spring_school.repo;

import com.example.spring_school.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 8/6/2023
    Project: spring_school_api
*/
public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor {
    Optional<Role> findByName(String user);
}
