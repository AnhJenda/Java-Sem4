package com.example.spring.controller;

import com.example.spring.dto.ProductDto;
import com.example.spring.dto.ProductStatic;
import com.example.spring.entity.Product;
import com.example.spring.properties.CommonProperties;
import com.example.spring.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController // => trả ra restAPI
@RequestMapping(value = "api/v1")
public class ProductController extends BaseController {
    private static Logger logger = LogManager.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;


    //View
//    @RequestMapping(method = RequestMethod.GET, value = "/products")
//    public ResponseEntity<?> getProducts(HttpServletRequest request) {
//        ProductDto criteria = new ProductDto();
//        criteria.setPageSize(commonProperties.getPageSize());
//        criteria.setPageNumber(commonProperties.getPageNumber());
//        ModelAndView view = new ModelAndView("product-list");
//        List<ProductDto> products = productService.gets(criteria);
//        view.addObject("products", products);
//        return ResponseEntity.ok(productService.gets(criteria));
//    }

    //@RequestMapping(method = RequestMethod.GET, value = "/products")
    @PostMapping(value = "/products")    // giong ben tren nhung ngan gon hon
    public ResponseEntity<?> gets (@RequestBody ProductDto criteria, HttpServletRequest request) {
        // nếu ko truyền paging thì ăn mặc định
//        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
//            criteria.setPageSize(commonProperties.getPageSize());
//        }
//        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
//            criteria.setPageNumber(commonProperties.getPageNumber());
//        }
        return ResponseEntity.ok(productService.gets(criteria));
    }

//    @GetMapping("/product")
//    public ModelAndView get(@RequestParam(required = false) Long id, HttpServletRequest request) {
//        logger.info("Process = request product by productId = {}", id);
//        ModelAndView view = new ModelAndView("product-detail");
//        ProductDto product = productService.getById(id);
//        view.addObject("product", product);
//        return view;
//    }

    @GetMapping("/product")
    public ResponseEntity<?> get(@RequestParam(required = false) Long id, HttpServletRequest request) {
        logger.info("Process = request product by productId = {}", id);
        if (!Objects.isNull(id)) {
            return ResponseEntity.ok(productService.getById(id));
        }
        return ResponseEntity.ok("No data");
    }

//    @GetMapping("/product/form")
//    public ModelAndView update(@RequestParam(required = false) Long id, HttpServletRequest request) {
//        ProductDto productDto = new ProductDto();
//        if (!Objects.isNull(id)) {
//            productDto = productService.getById(id);
//        }
//        ModelAndView view = new ModelAndView("form-product");
//        //ProductDto product = productService.getById(id);
//        view.addObject("product", productDto);
//        return view;
//    }

    @GetMapping("/product/form")
    public ResponseEntity<?>  update(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (!Objects.isNull(id)) {
            return ResponseEntity.ok(productService.getById(id));
        }
        return ResponseEntity.ok("No data");
    }

//    @PostMapping("/product/save")
//    public ModelAndView save(@ModelAttribute("productDto") ProductDto productDto ,HttpServletRequest request) {
//        // validate
//        ProductDto product =  productService.save(productDto);
//
//        if (Objects.isNull(product)) {
//            //
//        } else {
//            ModelAndView view = new ModelAndView("product-detail");
//            view.addObject("product", product);
//            return view;
//        }
//
////        ModelAndView view = new ModelAndView("product-detail");
////        //ProductDto product = productService.getById(productDto.getId());
////        view.addObject("product", product);
////        return view;
//        return null;
//    }

    @PostMapping("/product/save")
    public ResponseEntity<?> save(@ModelAttribute("productDto") ProductDto productDto ,HttpServletRequest request) {
        // validate
        ProductDto product =  productService.save(productDto);

        if (Objects.isNull(product)) {
            //
        } else {
            return ResponseEntity.ok(productService.save(productDto));
        }

        return ResponseEntity.ok("No data");
    }

//    @GetMapping("/product")
//    public ResponseEntity<?> delete(@RequestParam(required = false) Long id, HttpServletRequest request) {
//        if (!Objects.isNull(id)) {
//            return ResponseEntity.ok(productService.getById(id));
//        }
//        return ResponseEntity.ok(productService.getById(id));
//    }

//    @GetMapping(value = "/findProductStaticByName")
//    public ModelAndView findProductStaticByName (@RequestParam String productName, HttpServletRequest request) {
//        ModelAndView view = new ModelAndView("productStatic-detail");
//        ProductStatic product = productService.findByProductStaticName(productName);
//        view.addObject("product", product);
//        return view;
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "/products")
//    @GetMapping(value = "/products")    // giong ben tren nhung ngan gon hon
//    public ResponseEntity<?> gets
//    (@RequestBody ProductDto criteria, HttpServletRequest request) {
//        // nếu ko truyền paging thì ăn mặc định
//        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
//            criteria.setPageSize(commonProperties.getPageSize());
//        }
//        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
//            criteria.setPageNumber(commonProperties.getPageNumber());
//        }
//        return ResponseEntity.ok(productService.gets(criteria));
//    }
//
//    @GetMapping(value = "/findProductByName")
//    public ResponseEntity<?> findProductByName
//            (@RequestParam String productName, HttpServletRequest request) {
//        long startTime = System.currentTimeMillis();
//        ProductDto productDto = productService.findByProductName(productName);
//        long endTime = System.currentTimeMillis();
//        System.err.println("time process = " + (endTime - startTime));
//        return ResponseEntity.ok(productDto);
//    }


}

//@RequestBody : đối tượng đại diện cho boby request gửi lên

/*
-     Đưa ra danh sách các mặt hàng được ít nhất 2 công
ty cung cấp cho khách hàng “Hải Hà” . Thông tin được đưa ra bao gồm thông tin về mặt hàng và số công ty cung cấp
* */
