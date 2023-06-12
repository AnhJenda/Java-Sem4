package com.example.productlist.mapper.impl;

import com.example.productlist.dto.ProductDto;
import com.example.productlist.entity.Product;
import com.example.productlist.mapper.ProductMapper;

/*
    @author: Dinh Quang Anh
    Date   : 6/5/2023
    Project: ProductList
*/
public class ProductMapperImpl implements ProductMapper {

    public ProductDto entityToDto(Product p) {
        return new ProductDto();
    }
    public Product dtoToEntity (ProductDto p){
        return new Product();
    }
}
