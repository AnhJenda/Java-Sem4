package com.example.productlist.jpa.exception;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public class NoTableColumnFoundException extends RuntimeException{
    public NoTableColumnFoundException(String errorMessage){
        super(errorMessage);
    }
}
