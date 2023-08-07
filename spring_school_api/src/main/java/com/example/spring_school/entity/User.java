package com.example.spring_school.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String tel;
//    private String[] roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="user_team",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="team_id"))
    private List<Team> teamList;

    public User(String username, String email, String hashedPassword) {
        this.username = username;
        this.email = email;
        this.password = hashedPassword;
    }


    @Column(name = "inserted_time", nullable = true)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = true)
    private Date updatedTime;
    @Column(name = "inserted_by", nullable = true)
    private String insertedBy;
    @Column(name = "updated_by", nullable = true)
    private String updatedBy;

    @PrePersist
    private  void beforeInsert() {
        this.insertedTime = new Date();
    }

    @PreUpdate
    private  void beforeUpdate() {
        this.updatedTime = new Date();
    }

//    public User(long id, String username, String password, String email, String tel) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.tel = tel;
//    }

//    public List<GrantedAuthority> getAuthorities() {
//        int rolesTotal = roleList.size();
//        String[] roles = new String[roleList.size()];
//        for (int i = 0; i < rolesTotal; i++) {
//            roles[i] = roleList.get(i).getRole_name();
//        }

        // cách 1
//        List<GrantedAuthority> authorities =  new ArrayList<>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//
//        return authorities;

        // cách 2
//        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
}
