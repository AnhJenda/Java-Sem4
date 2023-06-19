package com.example.servlet_finalexam.controller;

import com.example.servlet_finalexam.dto.employeeDto;
import com.example.servlet_finalexam.service.EmployeeService;
import com.example.servlet_finalexam.service.Impl.EmployeeServiceImpl;

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
    Date   : 6/19/2023
    Project: Servlet_FinalExam
*/
@WebServlet(name = "EmployeeController", value = "/employees/*")
public class EmployeeController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            RequestDispatcher view = request.getRequestDispatcher("/jsp/list.jsp");

            final EmployeeService employeeService = new EmployeeServiceImpl();

            List<employeeDto> employees = employeeService.getListProduct();

            request.setAttribute("employees", employees);

            view.forward(request, response);
    }
}
