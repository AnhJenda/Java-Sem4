package com.example.jspservletsem4exercise.controller;

import com.example.jspservletsem4exercise.service.UserService;
import com.example.jspservletsem4exercise.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/*
    @author: Dinh Quang Anh
    Date   : 6/16/2023
    Project: jsp-servlet-sem4-exercise
*/
@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/jsp/login.jsp");

        view.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher view = request.getRequestDispatcher("/jsp/login.jsp");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        final UserService userService = new UserServiceImpl();

        if (!userService.authenUser(email, password)) {

            String stringErr = "Email or Password wrong!";
            request.setAttribute("error", stringErr);
            view.forward(request, response);


        }
        HttpSession session = request.getSession();
        session.setAttribute("email", email);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
