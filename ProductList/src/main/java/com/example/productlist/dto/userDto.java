package com.example.productlist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
@Data
@SuperBuilder
@NoArgsConstructor
public class userDto {
    private int id;
    private String username;
    private String password;
}
