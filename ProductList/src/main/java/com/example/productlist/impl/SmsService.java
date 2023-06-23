package com.example.productlist.impl;

/*
    @author: Dinh Quang Anh
    Date   : 6/21/2023
    Project: ProductList
*/
public class SmsService implements MessageService{
    @Override
    public void sendMessage(String message){
        System.err.println("SmS message" + message);
    }
}
