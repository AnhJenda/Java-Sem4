package com.example.productlist.impl;

/*
    @author: Dinh Quang Anh
    Date   : 6/21/2023
    Project: ProductList
*/
public class EmailService implements MessageService{
    @Override
    public void sendMessage(String message){
        System.err.println("Email message" + message);
    }
}
