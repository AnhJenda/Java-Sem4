package org.aptech.t2109e.springdemo.service.impl;

import org.aptech.t2109e.springdemo.dto.PageDto;
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
import java.util.Objects;
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
//    @Override
//    public List<ProductDto> getAll(ProductDto criteria){
//        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
//        Page<Product> products = productRepositoryInterface.findAll(pageable);
//        return products.getContent()
//                .stream()
//                .map(mapper :: EntityToDto)
//                .collect(Collectors.toList()); // getcontent sẽ trả ra listproduct
//    }

    @Override
    public PageDto<ProductDto> getAll(ProductDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Product> products = productRepositoryInterface.findAll(pageable);
        List<ProductDto> productDtos = products.getContent()
                .stream()
                .map(mapper::EntityToDto)
                .collect(Collectors.toList());

        PageDto<ProductDto> pageDto = new PageDto<>();
        pageDto.setPageSize(products.getSize());
        pageDto.setPageNumber(products.getNumber());
        pageDto.setTotalPages(products.getTotalPages());
        pageDto.setContent(productDtos);

        return pageDto;
    }




    @Override
    public ProductDto getById(Long id){
        // todo validate
        return mapper.EntityToDto(productRepositoryInterface.getById(id));
    }

    @Override
    public ProductDto save(ProductDto productDto){
 //       productRepositoryInterface.save()  // neu khoa chinh da ton tai -> update

                                                // neu khoa chinh null -> create
        Product product = mapper.DtoToEntity(productDto);
        if (Objects.isNull(product)){
            return null;
        }else {
            Product returnEntity = productRepositoryInterface.save(product);
            return mapper.EntityToDto(returnEntity);

          //  return Optional.ofNullable(productRepositoryInterface.save(product)).map(mapper::EntityToDto).orElse(null);
        }

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
