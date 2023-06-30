package org.aptech.t2109e.springdemo.service.impl;

import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;
import org.aptech.t2109e.springdemo.mapper.productMapper;
import org.aptech.t2109e.springdemo.repository.ProductRepositoryInterface;
import org.aptech.t2109e.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductRepositoryInterface productRepositoryInterface;
    @Autowired
    private productMapper mapper;
    @Override
    public List<ProductDto> getAll(ProductDto criteria){
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Product> products = productRepositoryInterface.findAll(pageable);
        return products.getContent()
                .stream()
                .map(mapper :: EntityToDto)
                .collect(Collectors.toList()); // getcontent sẽ trả ra listproduct
    }

    @Override
    public ProductDto findByName(String productName){
        Optional<Product> p = productRepositoryInterface.findByName(productName);

        return p.isPresent() ? mapper.EntityToDto(p.get()) : null;
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
