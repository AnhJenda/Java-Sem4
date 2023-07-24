package com.example.spring.entity;

import lombok.Data;

import javax.persistence.*;

/*
    @author: Dinh Quang Anh
    Date   : 7/21/2023
    Project: spring
*/
@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "tel", nullable = true)
    private String tel;


}
