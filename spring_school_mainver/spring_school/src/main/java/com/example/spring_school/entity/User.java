package com.example.spring_school.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roleList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="user_team",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="team_id"))
    private List<Team> teamList;

//    public User(long id, String username, String password, String email, String tel) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.tel = tel;
//    }

    public List<GrantedAuthority> getAuthorities() {
        int rolesTotal = roleList.size();
        String[] roles = new String[roleList.size()];
        for (int i = 0; i < rolesTotal; i++) {
            roles[i] = roleList.get(i).getRole_name();
        }

        // cách 1
//        List<GrantedAuthority> authorities =  new ArrayList<>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//
//        return authorities;

        // cách 2
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
