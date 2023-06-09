package com.example.productlist.jpa;

import java.sql.ResultSet;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/2/2023
    Project: ProductList
*/
public interface JpaExecutor <T> {
    List<T> findall();

    List<T> entityParser(ResultSet rs);
}