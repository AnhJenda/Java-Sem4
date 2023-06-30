package org.aptech.t2109e.springdemo.controller;

import org.aptech.t2109e.springdemo.config.properties.CommonProperties;
import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;
import org.aptech.t2109e.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@RestController
@RequestMapping(value = "api/v1")
public class ProductController extends BaseController{
    @Autowired
    private ProductService productService;
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    @GetMapping(value = "/products")    // giong ben tren nhung ngan gon hon
    public ResponseEntity<?> gets
            (@RequestBody ProductDto criteria, HttpServletRequest request){
        // nếu ko truyền paging thì ăn mặc định
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0){
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0){
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        return ResponseEntity.ok(productService.getAll(criteria));
    }

    @GetMapping(value = "/findProductByName")
    public ResponseEntity<?> findProductByName
            (@RequestParam String productName, HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        ProductDto productDto = productService.findByName(productName);
        long endTime = System.currentTimeMillis();
        System.err.println("time process = " + (endTime - startTime));
        return ResponseEntity.ok(productDto);
    }




    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
