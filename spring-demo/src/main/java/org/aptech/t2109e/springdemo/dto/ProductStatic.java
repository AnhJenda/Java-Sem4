package org.aptech.t2109e.springdemo.dto;

import java.math.BigDecimal;

/*
    @author: Dinh Quang Anh
    Date   : 6/26/2023
    Project: spring-demo
*/
public class ProductStatic {
    private String productName;
    private String producerName;
    private long id;
    private BigDecimal price;

    public ProductStatic(String productName,long id, BigDecimal price, String producerName) {
        this.productName = productName;
        this.producerName = producerName;
        this.id = id;
        this.price = price;
    }
}
