package org.aptech.t2109e.springdemo.repository;

import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.dto.ProductStatic;
import org.aptech.t2109e.springdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Repository
public interface ProductRepositoryInterface extends JpaRepository<Product, Long>, JpaSpecificationExecutor {
//    @Query("from product p where p.name = :name") // kiểu thông dụng
    // native query
    @Query(value = "select * from product where name =:name", nativeQuery = true)
    Optional<Product> findByN(@Param("name") String productName);
    @Query(value = "select"
            + " new ProductStatic(p.name, pr.producer_name)"
            + )
    Optional<ProductStatic> findByProductStatic(@Param("name") String productName);

}
