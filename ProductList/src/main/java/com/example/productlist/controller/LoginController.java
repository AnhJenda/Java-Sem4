package com.example.productlist.controller;

import com.example.productlist.Services.UserService;
import com.example.productlist.Services.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/*
    @author: Dinh Quang Anh
    Date   : 6/12/2023
    Project: ProductList
*/
@WebServlet(name = "loginservlet", value = "/login")
public class LoginController extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        RequestDispatcher view = req.getRequestDispatcher("/jsp/login.jsp");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        if (userService.authenUser(username, password)) {
            //todo: redirect -> login controller
            //todo: server return access_token (ttl) -> client lưu ở storage;
            res.sendRedirect(req.getContextPath() + "/home");
        } else {
            String stringErr = "";
            req.setAttribute("error", stringErr);
            view.forward(req, res);
        }
    }
}
