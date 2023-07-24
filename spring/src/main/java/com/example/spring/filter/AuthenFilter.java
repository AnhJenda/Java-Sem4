package com.example.spring.filter;

import com.example.spring.service.UserService;
import com.example.spring.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
    @author: Dinh Quang Anh
    Date   : 7/21/2023
    Project: spring
*/
public class AuthenFilter extends UsernamePasswordAuthenticationFilter {
    private static final String TOKEN_HEADER = "Authorization";
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtils jwtUtils;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN_HEADER);

        if(!StringUtils.isEmpty(token)){
            String username = jwtUtils.getUsernameFromToken(token);
            // check xem user tương ứng có tồn tại hay không
        }
    }

}
