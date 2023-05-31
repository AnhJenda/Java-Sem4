package org.aptech.t2109e.jspservlet;

/*
    @author: Dinh Quang Anh
    Date   : 5/26/2023
    Project: jsp-servlet
*/

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private String message;
    private String Username = "test123";
    private String Password = "123456";
    public void init() {
        message = "Login";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println();
        out.print("<h1>" + message + "</h1>");
        out.println("<form method=\"POST\" action=\"/jsp_servlet_war_exploded/login-servlet\">");
        out.println("<label>Username: </label>");
        out.println("<input type=\"text\" name=\"username\" place-holder=\"Enter username\"/> <br/>");
        out.print("<label>Password: </label>");
        out.println("<input type=\"password\" name=\"password\" place-holder=\"Enter password\"/>");
        out.println("<button type=\"submit\">Submit</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(request.getParameter("username").equals(Username)  && request.getParameter("password").equals(Password) ){
            out.println("<html><body>");
            out.println("<h3>Login Success!</h3>");
            out.println("<p>Hello, </p>" + request.getParameter("username") + "!");
            out.println("</body></html>");
        }
        else {
            out.println("<html><body>");
            out.println("<h3>Login Failed!</h3><br/>");
            out.println("<a href=\"login-servlet\">Login</a>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}
