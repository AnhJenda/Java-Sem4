package com.example.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductStatic {
    private String productName;
    private String producerName;

    public ProductStatic(String productName, String producerName) {
        this.productName = productName;
        this.producerName = producerName;
    }
}
