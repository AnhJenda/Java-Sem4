package com.example.spring.repository;

import com.example.spring.dto.ProductStatic;
import com.example.spring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    @author: Dinh Quang Anh
    Date   : 7/13/2023
    Project: spring
*/
@Repository
public interface ProductRepositoryInterface extends JpaRepository<Product, Long>, JpaSpecificationExecutor {
//Optional<List<Product>> findProductByName(String name);

    //    @Query("from product p where p.name = :name") // kiểu thông dụng
    // native query
//    @Query(value = "select * from product where name =:name limit :pageSize offset :offSet", nativeQuery = true)
//    Optional<Product> findByN(@Param("name") String productName,@Param("pageSize") int pageSize,@Param("offSet") int offSet);

    //    @Query(value = "select" +
//            " new ProductStatic(p.productName, pr.producerName)" +
//            "from Product p" +
//            " join Producer pr on pr.id = p.producer.id" +
//            " where p.name = :name")
    @Query(value = "select new com.example.spring.dto.ProductStatic(p.name, pr.productName) from Product p inner join Producer pr on pr.id = p.producer.id  where p.name = :name")
    Optional<ProductStatic> findByProductStatic(@Param("name") String productName);
    //@Query(value = "select * from product where name =:productName", nativeQuery = true)
    Optional<Product> findByName(@Param("name") String productName);
}
