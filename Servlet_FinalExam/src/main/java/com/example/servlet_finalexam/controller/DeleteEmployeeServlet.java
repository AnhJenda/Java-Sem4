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

/*
    @author: Dinh Quang Anh
    Date   : 6/26/2023
    Project: Servlet_FinalExam
*/
@WebServlet(name = "DeleteServlet", value = "/employees/delete")
public class DeleteEmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final EmployeeService employeeService = new EmployeeServiceImpl();

        String id = request.getParameter("id");

        int parsedId = Integer.parseInt(id);

        employeeService.deleteEmployee(parsedId);

        response.sendRedirect(request.getContextPath() + "/employees");
    }
}
