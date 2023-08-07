package com.example.demosecurity.service;

import com.example.demosecurity.entity.Role;
import com.example.demosecurity.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleService {
    @Autowired
    RoleRepository repository;

//    public Role getByName(String role) {
//        if (role == null) {
////            throw new BusinessException("201", "invalid param");
//        }
//        return repository.get;
//    }
}
