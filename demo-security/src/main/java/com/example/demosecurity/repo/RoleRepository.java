package com.example.demosecurity.repo;

import com.example.demosecurity.entity.Role;
import com.example.demosecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
