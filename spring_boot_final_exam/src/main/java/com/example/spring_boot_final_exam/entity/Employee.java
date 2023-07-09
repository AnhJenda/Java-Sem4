package com.example.spring_boot_final_exam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
@Entity(name = "employee")
@NoArgsConstructor
@SuperBuilder
@Data
public class Employee {
    @Id
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "insert_time", nullable = true)
    private LocalDateTime insertedTime;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_time", nullable = true)
    private LocalDateTime updatedTime;
    @Column(name = "update_by")
    private String updateBy;

    @PrePersist
    public void beforeInsert(){
        this.insertedTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }
    @PreUpdate
    public void beforeUpdate(){
        this.updatedTime = LocalDateTime.now();
    }
}
