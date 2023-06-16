package com.example.day4.impl;

import com.example.day4.Entity.Product;

/*
    @author: Dinh Quang Anh
    Date   : 6/14/2023
    Project: day4
*/
public class ProductRepositoryImpl extends JpaExecutorImpl<Product>{
    public ProductRepositoryImpl() {
        super(Product.class);
    }

    public Object getAll() {
        return findall();
    }

    @Override
    public Product getById(int id){
        return super.getById(id); // có nguy cơ gây ra đệ quy
    }

    public static void main(String[] args) {
        ProductRepositoryImpl obj = new ProductRepositoryImpl();
        obj.getById(1);
    }
}