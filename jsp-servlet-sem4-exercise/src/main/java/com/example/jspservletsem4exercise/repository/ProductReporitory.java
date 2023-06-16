package com.example.jspservletsem4exercise.repository;

import com.example.jspservletsem4exercise.entity.Product;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/
public interface ProductReporitory {
    List<Product> gets();
//    List<Product> productDetails();

    /*List<Product> getProducts(int pageNumber, int pageSize, String searchName);
    int getTotalProducts(String searchName);*/
}
