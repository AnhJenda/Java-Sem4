package com.example.jspservletsem4exercise.service;

import com.example.jspservletsem4exercise.dto.ProductDto;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
public interface ProductService {
    List<ProductDto> getListProduct();
}
