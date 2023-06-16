package com.example.jspservletsem4exercise.anotation;

import com.example.jspservletsem4exercise.constant.SqlDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    // mapping attribute trong application với column trong bảng của dtb
    String name();
    SqlDataType dataType();
}
