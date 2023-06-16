package com.example.day4.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    @author: Dinh Quang Anh
    Date   : 6/2/2023
    Project: ProductList
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
    // Primary key của table
    String name();
}
