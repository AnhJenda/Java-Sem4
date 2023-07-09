package org.aptech.t2109e.springdemo.controller;

import org.aptech.t2109e.springdemo.config.properties.CommonProperties;
import org.aptech.t2109e.springdemo.dto.PageDto;
import org.aptech.t2109e.springdemo.dto.ProductDto;
import org.aptech.t2109e.springdemo.entity.Product;
import org.aptech.t2109e.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/23/2023
    Project: spring-demo
*/
@Controller
//@RequestMapping(value = "api/v1")
public class ProductController extends BaseController{
    @Autowired
    private ProductService productService;
//    @RequestMapping(method = RequestMethod.GET, value = "/products")
//    @GetMapping(value = "/products")    // giong ben tren nhung ngan gon hon
//    public ResponseEntity<?> gets
//            (@RequestBody ProductDto criteria, HttpServletRequest request){
//        // nếu ko truyền paging thì ăn mặc định
//        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0){
//            criteria.setPageSize(commonProperties.getPageSize());
//        }
//        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0){
//            criteria.setPageNumber(commonProperties.getPageNumber());
//        }
//        return ResponseEntity.ok(productService.getAll(criteria));
//    }

    @GetMapping(value = "/findProductByName")
    public ResponseEntity<?> findProductByName
            (@RequestParam String productName, HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        ProductDto productDto = productService.findByName(productName);
        long endTime = System.currentTimeMillis();
        System.err.println("time process = " + (endTime - startTime));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping(value = "/product-list")
    public ModelAndView getProductList(HttpServletRequest request,
                                       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        ProductDto criteria = new ProductDto();

        // Nếu không truyền paging thì lấy giá trị mặc định từ commonProperties
        if (pageNumber == null || pageNumber < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        } else {
            criteria.setPageNumber(pageNumber);
        }
        if (pageSize == null || pageSize <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        } else {
            criteria.setPageSize(pageSize);
        }

        ModelAndView view = new ModelAndView("jsp/product-list");
        PageDto<ProductDto> productPage = productService.getAll(criteria);

        view.addObject("products", productPage.getContent());
        view.addObject("pages", productPage);

        return view;
    }


    @GetMapping("/product")
    public ModelAndView get(@RequestParam(required = false) long id, HttpServletRequest request){
        ModelAndView view = new ModelAndView("jsp/details");
        ProductDto productDto = productService.getById(id);
        view.addObject("product", productDto);
        return view;
    }



//    @PostMapping
//    public Product createProduct(@RequestBody ProductDto productDto) {
//        return productService.createProduct(productDto);
//    }
//
//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
//        return productService.updateProduct(id, productDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Long id) {
//        return productService.getProductById(id);
//    }
}
