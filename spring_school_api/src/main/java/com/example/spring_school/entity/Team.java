package com.example.spring_school.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "team")
@Data
@SuperBuilder
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String team_name;

    @ManyToMany(mappedBy = "teamList")
    List<User> userList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="team_role",
            joinColumns = @JoinColumn(name="team_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roleList;


    @Column(name = "inserted_time", nullable = false)
    private Date insertedTime;
    @Column(name = "updated_time", nullable = false)
    private Date updatedTime;
    @Column(name = "inserted_by", nullable = false)
    private String insertedBy;
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @PrePersist
    private  void beforeInsert() {
        this.insertedTime = new Date();
    }

    @PreUpdate
    private  void beforeUpdate() {
        this.updatedTime = new Date();
    }
}
