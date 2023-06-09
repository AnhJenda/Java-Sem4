package com.example.productlist.Repositories.iml;

import com.example.productlist.Repositories.ProductReporitory;
import com.example.productlist.entity.Product;
import com.example.productlist.jpa.iml.JpaExecutorImplement;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/2/2023
    Project: ProductList
*/
public class ProductRepositoryImpl  extends JpaExecutorImplement<Product> implements ProductReporitory {
    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> gets() {
        return findall();
    }
}