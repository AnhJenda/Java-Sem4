package com.example.productlist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
    @author: Dinh Quang Anh
    Date   : 6/5/2023
    Project: ProductList
*/
//@Data
@SuperBuilder
@NoArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private String category;
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
