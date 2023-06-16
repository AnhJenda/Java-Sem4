package com.example.jspservletsem4exercise.jpa;

import java.sql.ResultSet;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
public interface JpaExecutor <T> {
    List<T> findall();
    T getById(int id);
    T getByField(String fieldName, String valueName);
    List<T> entityParser(ResultSet rs);
}