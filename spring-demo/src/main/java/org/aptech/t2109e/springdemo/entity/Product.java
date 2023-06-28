package org.aptech.t2109e.springdemo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Entity(name = "product")
@NoArgsConstructor
@SuperBuilder
@Data
public class Product {
    @Id
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private long price;

    // format
    @Column(name = "insert_time", nullable = true)
    private LocalDateTime insertedTime;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_time", nullable = true)
    private LocalDateTime updatedTime;
    @Column(name = "update_by")
    private String updateBy;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    // fetch = eager: khi persitantContext load Product thì sẽ load luôn Product.Producer
    private Producer producer;

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
