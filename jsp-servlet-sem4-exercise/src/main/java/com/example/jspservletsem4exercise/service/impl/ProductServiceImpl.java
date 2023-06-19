package com.example.jspservletsem4exercise.service.impl;

import com.example.jspservletsem4exercise.entity.Product;
import com.example.jspservletsem4exercise.jpa.impl.JpaExecutorImplement;
import com.example.jspservletsem4exercise.mapper.ProductMapperImpl;
import com.example.jspservletsem4exercise.service.ProductService;
import com.example.jspservletsem4exercise.repository.impl.ProductRepositoryImpl;
import com.example.jspservletsem4exercise.dto.ProductDto;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

/*    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/


public class ProductServiceImpl implements ProductService {
    ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
    ProductMapperImpl mapper = new ProductMapperImpl();
    @Override
    public List<ProductDto> getListProduct(){
        return productRepository.gets().stream().map(p -> mapper.entityToDto(p)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProduct(int id) {
        Product product = productRepository.getProduct(id);
        if (product != null) {
            return mapper.entityToDto(product);
        }
        return null;
    }
}
