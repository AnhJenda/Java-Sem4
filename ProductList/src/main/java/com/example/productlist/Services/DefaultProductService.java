package com.example.productlist.Services;

import com.example.productlist.Product;
import com.example.productlist.Repositories.ProductReporitory;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/
public class DefaultProductService implements ProductService{
    private ProductReporitory productRepository;

    public DefaultProductService(ProductReporitory productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(int pageNumber, int pageSize, String searchName) {
        return productRepository.getProducts(pageNumber, pageSize, searchName);
    }

    @Override
    public int getTotalPages(int pageSize, String searchName) {
        int totalProducts = productRepository.getTotalProducts(searchName);
        return (int) Math.ceil((double) totalProducts / pageSize);
    }
}
