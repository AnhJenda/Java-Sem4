package com.example.productlist.mapper;

import com.example.productlist.dto.ProductDto;
import com.example.productlist.entity.Product;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T20:59:42+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
*/
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product dtoToEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }

    @Override
    public ProductDto entityToDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        return productDto;
    }
}
