package com.example.servlet_finalexam.jpa.exception;

/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
public class NoTableColumnFoundException extends RuntimeException{
    public NoTableColumnFoundException(String errorMessage){
        super(errorMessage);
    }
}
