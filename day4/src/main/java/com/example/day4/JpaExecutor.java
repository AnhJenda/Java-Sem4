package com.example.day4;

import java.sql.ResultSet;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/14/2023
    Project: day4
*/
public interface JpaExecutor <T> {
    List<T> findall();
    T getById(int id);
    List<T> entityParser(ResultSet rs);
}
