package com.example.productlist.controller;


import javax.servlet.*;
import java.io.IOException;

/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("do filter");
        // todo: get access_token, decode
        // check authen fail -> chuyển hướng tới đăng nhập lại


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
