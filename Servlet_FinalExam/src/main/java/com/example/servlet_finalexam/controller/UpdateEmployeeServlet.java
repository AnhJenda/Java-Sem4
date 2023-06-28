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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    @author: Dinh Quang Anh
    Date   : 6/26/2023
    Project: Servlet_FinalExam
*/
@WebServlet(name = "UpdateEmployee", value = "/employees/update")
public class UpdateEmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/jsp/update.jsp");
        final EmployeeService employeeService = new EmployeeServiceImpl();

        String id = request.getParameter("id");

        int parsedId = Integer.parseInt(id);

        employeeDto employeeDto = employeeService.getEmployeeById(parsedId);

        request.setAttribute("employee", employeeDto);

        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        final EmployeeService employeeService = new EmployeeServiceImpl();

        String id = request.getParameter("id");

        int parsedId = Integer.parseInt(id);
        employeeDto employeedto = employeeService.getEmployeeById(parsedId);

        String fullName = request.getParameter("fullname");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        String birthdayString = request.getParameter("birthday");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date birthday = null;
        try {
            birthday = dateFormat.parse(birthdayString);
        } catch (ParseException e) {
            // Xử lý lỗi chuyển đổi ngày tháng
            e.printStackTrace();
        }
        employeeDto employeeDto = new employeeDto();
        employeeDto.setFullname(fullName != null ? fullName : employeedto.getFullname());
        employeeDto.setBirthday(birthday != null ? birthday : employeedto.getBirthday());
        employeeDto.setAddress(address != null ? address : employeedto.getAddress());
        employeeDto.setPosition(position != null ? position : employeedto.getPosition());
        employeeDto.setDepartment(department != null ? department : employeedto.getDepartment());

        employeeService.updateEmployee(parsedId, employeeDto);
        response.sendRedirect(request.getContextPath() + "/employees");
    }
}
