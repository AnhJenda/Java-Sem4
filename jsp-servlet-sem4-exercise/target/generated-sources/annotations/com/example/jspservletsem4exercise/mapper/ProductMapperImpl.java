package com.example.jspservletsem4exercise.mapper;

import com.example.jspservletsem4exercise.dto.ProductDto;
import com.example.jspservletsem4exercise.entity.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-18T21:54:33+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product dtoToEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );
        product.setLength( productDto.getLength() );
        product.setWidth( productDto.getWidth() );
        product.setHeight( productDto.getHeight() );
        product.setManufacturerId( productDto.getManufacturerId() );

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
        productDto.setLength( product.getLength() );
        productDto.setWidth( product.getWidth() );
        productDto.setHeight( product.getHeight() );
        productDto.setManufacturerId( product.getManufacturerId() );

        return productDto;
    }
}
