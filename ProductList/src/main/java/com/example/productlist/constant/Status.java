package com.example.productlist.constant;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public enum Status {
    ACTIVE(1),
    INACTIVE(0);
    public int value;
    private Status(int value) {this.value = value;}
}
