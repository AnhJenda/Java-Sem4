package com.example.servlet_finalexam.jpa;

import com.example.servlet_finalexam.entity.Employee;

import java.sql.ResultSet;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
public interface JpaExecutor <T> {
    List<T> findall();

    // Create
    void createNewRecord(T object);
    // Update
    void updateRecord(int id, T object);

    T getById(int id);

    void deleteRecord(int id);

    List<T> entityParser(ResultSet rs);
}
