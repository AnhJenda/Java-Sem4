package com.example.jspservletsem4exercise.repository.impl;

import com.example.jspservletsem4exercise.entity.Product;
import com.example.jspservletsem4exercise.repository.ProductReporitory;
import com.example.jspservletsem4exercise.entity.Product;
import com.example.jspservletsem4exercise.jpa.impl.JpaExecutorImplement;
import com.example.jspservletsem4exercise.jpa.impl.JpaExecutorImplement;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/2/2023
    Project: ProductList
*/
public class ProductRepositoryImpl extends JpaExecutorImplement<Product> implements ProductReporitory {
    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> gets() {
        return super.findall();
    }

    @Override
    public  Product getProduct(int id){
        return super.getById(id);
    }
}