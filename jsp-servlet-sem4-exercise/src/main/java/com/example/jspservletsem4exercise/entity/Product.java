package com.example.jspservletsem4exercise.entity;

import com.example.jspservletsem4exercise.anotation.Column;
import com.example.jspservletsem4exercise.anotation.Entity;
import com.example.jspservletsem4exercise.anotation.Id;
import com.example.jspservletsem4exercise.constant.SqlDataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
@Data
@SuperBuilder
@NoArgsConstructor
@Entity(tablename = "product")
public class Product {
    @Id(name = "id", dataType = SqlDataType.INTEGER)
    private int id;
    @Column(name = "name", dataType = SqlDataType.TEXT)
    private String name;
    @Column(name = "price", dataType = SqlDataType.DOUBLE)
    private double price;
    @Column(name = "length", dataType = SqlDataType.FLOAT)
    private float length;
    @Column(name = "width", dataType = SqlDataType.FLOAT)
    private float width;
    @Column(name = "height", dataType = SqlDataType.FLOAT)
    private float height;
    @Column(name = "manufacturerId", dataType = SqlDataType.INTEGER)
    private int manufacturerId;
}
