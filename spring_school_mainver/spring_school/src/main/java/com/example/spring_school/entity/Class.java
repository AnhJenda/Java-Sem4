package com.example.spring_school.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "class_table")
@Data
@SuperBuilder
@NoArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_class", unique = true, nullable = false)
    private String name;
    @Column(name = "code_class", unique = true, nullable = false)
    private String code;
    @Column(name = "start_time_class", nullable = false)
    private Date startTime;
    @Column(name = "end_time_class", nullable = false)
    private Date endTime;
    @Column(name = "current_semester_class", nullable = false)
    private int currentSemester;
    @Column(name = "size_class", nullable = false)
    private int size;

    //fetch: LAZY khi load thì mới lấy thằng quan hệ
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "student_class",
//            joinColumns = { @JoinColumn(name = "class_id") },
//            inverseJoinColumns = {@JoinColumn(name = "student_id") })
//    private Set<Student> students = new HashSet<>();
    @ManyToMany(mappedBy = "classes")
    List<Student> students;


}

