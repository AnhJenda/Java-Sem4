package com.example.productlist.Repositories;

import com.example.productlist.entity.Product;

import java.util.Arrays;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/
public interface ProductReporitory {
    List<Product> gets();

    /*List<Product> getProducts(int pageNumber, int pageSize, String searchName);
    int getTotalProducts(String searchName);*/
}
