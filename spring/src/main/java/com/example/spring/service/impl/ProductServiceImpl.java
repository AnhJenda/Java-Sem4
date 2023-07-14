package com.example.spring.service.impl;

import com.example.spring.dto.ProductDto;
import com.example.spring.dto.ProductStatic;
import com.example.spring.entity.Product;
import com.example.spring.exception.BusinessException;
import com.example.spring.mapper.ProductMapper;
import com.example.spring.repository.ProductRepositoryInterface;
import com.example.spring.service.ProductService;
import com.example.spring.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private ProductRepositoryInterface repo;
    @Autowired
    private ProductSpecification specification;
    @Override
    public List<ProductDto> gets(ProductDto criteria) {
        if (criteria.getPageNumber() < 0 || criteria.getPageNumber() < 0)
            throw new BusinessException("201", "invalid param");
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Product> products = repo.findAll(specification.filter(criteria),pageable);
        return products.getContent()
                .stream()
                .map(mapper :: entityDto)
                .collect(Collectors.toList()); // getcontent sẽ trả ra listproduct
    }

    @Override
    public ProductDto getById(Long id) {
        // todo validate
        return mapper.entityDto(repo.getById(id));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        //repo.save() // nếu khóa chính tồn tại -> update
                      // nếu khóa chính null -> create
        Product product = mapper.dtoEntity(productDto);
        if (Objects.isNull(product)) {
            return null;
        } else  {
            //return Optional.ofNullable(repo.save(product)).map(mapper::entityDto).orElse(null);
            Product returnEntity = repo.save(product);
            return mapper.entityDto(returnEntity);
        }
        //return null;
    }

    @Override
    public ProductDto findByProductName(String productName) {
        Optional<Product> p = repo.findByName(productName);
//        if (p.isPresent()) {
//            return mapper.entityDto(p.get());
//        } else  {
//            return null;
//        }
        return p.isPresent() ? mapper.entityDto(p.get()) : null;
    }

    @Override
    public ProductStatic findByProductStaticName(String productName) {
        Optional<ProductStatic> ps = repo.findByProductStatic(productName);
        return ps.isPresent() ? ps.get() : null;
    }
}
