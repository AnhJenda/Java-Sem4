package com.example.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto  extends PageDto{
    private long id;
    private String name;
    private long price;
    private LocalDateTime insertedTime;
    private String createdBy;
    private LocalDateTime updatedTime;
    private String updatedBy;
    @JsonIgnore
    private long minPrice;
    @JsonIgnore
    private long maxPrice;
}
