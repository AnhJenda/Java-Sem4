package com.example.spring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@SuperBuilder
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private long price;


    // format
    @Column(name = "inserted_time", nullable = true)
    private LocalDateTime insertedTime;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_time", nullable = true)
    private LocalDateTime updatedTime;
    @Column(name = "updated_by")
    private String updatedBy;
    @PrePersist
    private  void beforeInsert() {
        this.insertedTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    private  void beforeUpdate(){
        this.updatedTime = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    // fetch = eager: khi persitantContext load Product thì sẽ load luôn Product.Producer
    private Producer producer;
}
