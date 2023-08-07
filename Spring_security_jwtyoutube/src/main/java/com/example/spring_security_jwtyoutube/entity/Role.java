package com.example.spring_security_jwtyoutube.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 8/2/2023
    Project: Spring_security_jwtyoutube
*/
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="role_name")
    private String role_name;

    @ManyToMany(mappedBy = "roleList")
    List<User> userList;

//    @ManyToMany(mappedBy = "roleList")
//    List<Team> teamList;
}
