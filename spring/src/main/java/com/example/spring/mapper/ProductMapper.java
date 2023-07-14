package com.example.spring.mapper;

import com.example.spring.dto.ProductDto;
import com.example.spring.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtoEntity(ProductDto productDto);
    ProductDto entityDto(Product product);
}
