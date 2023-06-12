package com.example.productlist.jpa.exception;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
