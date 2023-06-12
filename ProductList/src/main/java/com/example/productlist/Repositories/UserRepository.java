package com.example.productlist.Repositories;

import com.example.productlist.entity.User;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public interface UserRepository {
    User getByUserName(String username);
}
