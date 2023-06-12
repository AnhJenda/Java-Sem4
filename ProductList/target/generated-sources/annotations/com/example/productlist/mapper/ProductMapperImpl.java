package com.example.productlist.mapper;

import com.example.productlist.dto.ProductDto;
import com.example.productlist.entity.Product;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T20:23:41+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
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

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setCategory( product.getCategory() );
        productDto.setStatus( product.getStatus() );

        return productDto;
    }
}
