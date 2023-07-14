package org.aptech.t2109e.springdemo.service;

import org.aptech.t2109e.springdemo.dto.PageDto;
import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
public interface ProductService {
    List<ProductDto> getAll(ProductDto criteria);  // hiếm khi lấy getall() -> phân trang -> dùng offset để phân trang trong sql

    ProductDto findByName(String productName);
    ProductDto getById(Long id);
    ProductDto save (ProductDto productDto);


    Product createProduct(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    Product getProductById(Long id);
}
