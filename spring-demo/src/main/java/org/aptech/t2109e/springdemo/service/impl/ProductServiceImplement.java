package org.aptech.t2109e.springdemo.service.impl;

import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;
import org.aptech.t2109e.springdemo.mapper.productMapper;
import org.aptech.t2109e.springdemo.mapper.productMapperImpl;
import org.aptech.t2109e.springdemo.repository.ProductRepositoryInterface;
import org.aptech.t2109e.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductRepositoryInterface productRepositoryInterface;
    productMapper mapper = new productMapperImpl();
    @Override
    public List<Product> getAll(){
        List<Product> products = productRepositoryInterface.findAll();
        return products;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = mapper.DtoToEntity(productDto);
        return productRepositoryInterface.save(product);
    }
    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepositoryInterface.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Product product = mapper.DtoToEntity(productDto);

        return productRepositoryInterface.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepositoryInterface.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepositoryInterface.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}
