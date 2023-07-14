package com.example.spring.service;

import com.example.spring.dto.ProductDto;
import com.example.spring.dto.ProductStatic;

import java.util.List;


public interface ProductService {
    List<ProductDto> gets(ProductDto criteria); // hiếm trường hợp lấy getAll -> vì nhiều quá cần phải phân trang
    ProductDto getById(Long id);
    ProductDto save(ProductDto product);
    ProductDto findByProductName(String productName);

    ProductStatic findByProductStaticName(String productName);
}
