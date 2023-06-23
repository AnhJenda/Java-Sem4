package com.example.productlist.impl;

/*
    @author: Dinh Quang Anh
    Date   : 6/21/2023
    Project: ProductList
*/
public class UserService {
    MessageService messageService;

    public UserService(MessageService messageService) {
        this.messageService = messageService;
    }
    public void registerUser(){
        System.err.println("Register User");
        messageService.sendMessage("Register Success");
    }
}
