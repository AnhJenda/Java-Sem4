package com.example.jspservletsem4exercise.mapper;

import com.example.jspservletsem4exercise.dto.ProductDto;
import com.example.jspservletsem4exercise.entity.Product;
import org.mapstruct.Mapper;

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
