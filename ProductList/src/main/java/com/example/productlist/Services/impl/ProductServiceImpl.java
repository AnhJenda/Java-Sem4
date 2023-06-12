package com.example.productlist.Services.impl;

import com.example.productlist.Repositories.iml.ProductRepositoryImpl;
import com.example.productlist.Services.ProductService;
import com.example.productlist.dto.ProductDto;
import com.example.productlist.entity.Product;
import com.example.productlist.mapper.impl.ProductMapperImpl;

import java.util.List;
import java.util.stream.Collectors;

/*
    @author: Dinh Quang Anh
    Date   : 6/5/2023
    Project: ProductList
*/
public class ProductServiceImpl implements ProductService {
    ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
    ProductMapperImpl mapper = new ProductMapperImpl();
    @Override
    public List<ProductDto> getListProduct(){
        return productRepository.gets().stream().map(p -> mapper.entityToDto(p)).collect(Collectors.toList());
    }
}
