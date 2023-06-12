package com.example.productlist;

import com.example.productlist.Repositories.ProductReporitory;
import com.example.productlist.Repositories.iml.ProductRepositoryImpl;
import com.example.productlist.Services.ProductService;
import com.example.productlist.Services.impl.ProductServiceImpl;
import com.example.productlist.dto.ProductDto;
import com.example.productlist.entity.Product;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public class Main {
    public static void main(String[] args) {
        ProductReporitory productReporitory = new ProductRepositoryImpl();

        List<Product> productList = productReporitory.gets();
        for (Product productDto : productList) {
            System.err.println(productDto.getId());
            System.err.println(productDto.getName());
            System.err.println(productDto.getCategory());
            System.err.println(productDto.getStatus());
            System.err.println(productDto.getPrice());
        }

//        System.err.println(productList);
    }
}
