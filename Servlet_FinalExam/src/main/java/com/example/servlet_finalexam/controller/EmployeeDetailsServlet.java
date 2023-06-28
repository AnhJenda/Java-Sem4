package com.example.servlet_finalexam.controller;

import com.example.servlet_finalexam.dto.employeeDto;
import com.example.servlet_finalexam.entity.Employee;
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
    Date   : 6/28/2023
    Project: Servlet_FinalExam
*/
@WebServlet(name = "Details", value = "/employees/details")
public class EmployeeDetailsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final EmployeeService employeeService = new EmployeeServiceImpl();
        RequestDispatcher view = request.getRequestDispatcher("/jsp/employeeDetails.jsp");

        String id = request.getParameter("id");
        int parsedId = Integer.parseInt(id);
        employeeDto employeeDto = employeeService.getEmployeeById(parsedId);

        request.setAttribute("employee", employeeDto);

        view.forward(request, response);
    }
}
