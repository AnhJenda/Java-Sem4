package com.example.productlist.Services;

import com.example.productlist.Product;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/
public interface ProductService {
    List<Product> getProducts(int pageNumber, int pageSize, String searchName);
    int getTotalPages(int pageSize, String searchName);
}
