package com.example.productlist.Services;

import com.example.productlist.dto.ProductDto;
import com.example.productlist.entity.Product;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/
public interface ProductService {
    List<ProductDto> getListProduct();
}
