package org.aptech.t2109e.springdemo.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Entity(name = "product")
public class Product {
    @Id
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private long price;

    // format
    @Column(name = "insert_time", nullable = false)
    private LocalDateTime insertedTime;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_time", nullable = false)
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