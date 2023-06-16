package com.example.day4.Entity;

import com.example.day4.anotations.Column;
import com.example.day4.anotations.Entity;
import com.example.day4.anotations.Id;
import com.example.day4.constant.SqlDataType;

/*
    @author: Dinh Quang Anh
    Date   : 6/14/2023
    Project: day4
*/
@Entity(tablename = "product")
public class Product {
    @Id(name = "id")
    private int id;
    @Column(name = "product_name", dataType = SqlDataType.TEXT)
    private String name;

}
