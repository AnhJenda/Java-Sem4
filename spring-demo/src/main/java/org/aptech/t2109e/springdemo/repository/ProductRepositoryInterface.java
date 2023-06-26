package org.aptech.t2109e.springdemo.repository;

import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Repository
public interface ProductRepositoryInterface extends JpaRepository<Product, Long>, JpaSpecificationExecutor {

}
