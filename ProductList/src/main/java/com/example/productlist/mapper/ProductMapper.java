package com.example.productlist.mapper;

import com.example.productlist.dto.ProductDto;
import com.example.productlist.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/5/2023
    Project: ProductList
*/
@Mapper
public interface ProductMapper {
    // 2 hàm: convert từ dto ra entity và ngược lại
    Product dtoToEntity(ProductDto productDto);
    ProductDto entityToDto(Product product);
}
