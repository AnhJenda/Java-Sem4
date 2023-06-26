package org.aptech.t2109e.springdemo.service;

import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
public interface ProductService {
    List<Product> getAll();
    Product createProduct(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    Product getProductById(Long id);
}
