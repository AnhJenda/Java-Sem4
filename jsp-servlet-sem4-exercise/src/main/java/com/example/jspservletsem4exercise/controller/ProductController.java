package com.example.jspservletsem4exercise.controller;

import com.example.jspservletsem4exercise.dto.ProductDto;
import com.example.jspservletsem4exercise.service.ProductService;
import com.example.jspservletsem4exercise.service.UserService;
import com.example.jspservletsem4exercise.service.impl.ProductServiceImpl;
import com.example.jspservletsem4exercise.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 6/18/2023
    Project: jsp-servlet-sem4-exercise
*/
@WebServlet(name = "ProductController", value = "/products/*")
public class ProductController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null) {
            RequestDispatcher view = request.getRequestDispatcher("/jsp/productList.jsp");

            final ProductService productService = new ProductServiceImpl();

            List<ProductDto> products = productService.getListProduct();

            request.setAttribute("products", products);

            view.forward(request, response);
        }
        else {
            RequestDispatcher view = request.getRequestDispatcher("jsp/productDetails.jsp");
            final ProductService productService = new ProductServiceImpl();
            ProductDto product = productService.getProduct(Integer.parseInt(id));
            request.setAttribute("product", product);
            view.forward(request, response);
        }

    }
}
