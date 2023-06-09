package com.example.productlist.entity;

import com.example.productlist.anotation.Column;
import com.example.productlist.anotation.Entity;
import com.example.productlist.anotation.Id;
import com.example.productlist.constant.SqlDataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/
@Data
@NoArgsConstructor
@SuperBuilder
@Entity(tablename = "product")
public class Product {
    @Id(name = "product_id")
    private int id;
    @Column(name = "name", dataType = SqlDataType.TEXT)
    private String name;
    @Column(name = "price", dataType = SqlDataType.DOUBLE)
    private double price;
}
