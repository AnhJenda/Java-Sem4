package org.aptech.t2109e.springdemo.mapper;

import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;
import org.mapstruct.Mapper;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Mapper
public interface productMapper {
    Product DtoToEntity(ProductDto productDto);

    ProductDto EntityToDto(Product product);
}
