package com.example.spring_school.entity;

import com.example.spring_school.constant.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student_table")
@Data
@SuperBuilder
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name_student", nullable = false)
    private String name;
    @Column(name = "code_student", nullable = false)
    private String code;
    @Column(name = "birth_date_student")
    private String birthDate;
    @Column(name = "gender_student")
    private int gender;
    @Column(name = "address_student")
    private String address;
    @Column(name = "tel_student")
    private String tel;

    @PrePersist
    public void beforeInsert() {
        if (gender != Gender.MALE.val && gender != Gender.FEMALE.val && gender != Gender.ORTHER.val) {
            this.gender = Gender.ORTHER.val;
        }
    }
    @PreUpdate
    public void beforeUpdate() {
        if (gender != Gender.MALE.val && gender != Gender.FEMALE.val && gender != Gender.ORTHER.val) {
            this.gender = Gender.ORTHER.val;
        }
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_class",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "class_id")})
    private List<Class> classes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_skill",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skills;

}
