package com.example.spring_school.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "skill_table")
@Data
@SuperBuilder
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_skill", nullable = false)
    private String name;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "student_skill",
//            joinColumns = { @JoinColumn(name = "class_id") },
//            inverseJoinColumns = {@JoinColumn(name = "skill_id") })
//    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "skills")
    List<Student> students;
}

