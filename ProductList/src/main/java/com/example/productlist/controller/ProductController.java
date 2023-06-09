package com.example.productlist.controller;

import com.example.productlist.Services.ProductService;
import com.example.productlist.Services.impl.ProductServiceImpl;
import com.example.productlist.dto.ProductDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/

public class ProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductService productService = new ProductServiceImpl();

        List<ProductDto> productList = productService.getListProduct();
        request.setAttribute("products", productList);

        int num = 2;
        request.setAttribute("number", num);


        RequestDispatcher view = request.getRequestDispatcher("/jsp/product-list.jsp");
        view.forward(request, response);
    }
}
