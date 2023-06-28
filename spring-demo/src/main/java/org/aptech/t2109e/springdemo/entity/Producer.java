package org.aptech.t2109e.springdemo.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/26/2023
    Project: spring-demo
*/
@Entity(name = "producer")
@SuperBuilder
@NoArgsConstructor
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "producer_name",nullable = false)
    private String productName;
    @Column(name = "address",nullable = true)
    private String address;
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    // mappedBy: foreign key là trường gì
    private List<Product> products;
}
