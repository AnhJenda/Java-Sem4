package com.example.productlist.Repositories.iml;

import com.example.productlist.Repositories.UserRepository;
import com.example.productlist.entity.User;
import com.example.productlist.jpa.iml.JpaExecutorImplement;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public class UserRepositoryImpl extends JpaExecutorImplement<User> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User getByUserName(String userName){
        return super.getByField("username", userName);
    }
}
