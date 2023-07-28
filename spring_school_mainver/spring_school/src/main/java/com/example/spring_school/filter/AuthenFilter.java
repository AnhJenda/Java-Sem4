package com.example.spring_school.filter;

import com.example.spring_school.entity.User;
import com.example.spring_school.service.UserService;
import com.example.spring_school.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenFilter extends UsernamePasswordAuthenticationFilter {
    private static final String TOKEN_HEADER = "Authorization";
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserService userService;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token  = httpServletRequest.getHeader(TOKEN_HEADER);
        if (!StringUtils.isEmpty(token)) { // check token có valid hay không
            String username = jwtUtils.getUsernameFromToken(token);
            // check user có tồn tại hay không
            User user = userService.getByName(username);
            if (user != null) {
                // mapping user defined - user in spring security
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialNonExpired = true;
                boolean accountNonLocked = true;

                //UserDetails: chứa thông tin cần thiết để tạo ra Authentication
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                        enabled, accountNonExpired, credentialNonExpired, accountNonLocked, user.getAuthorities());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request,response);
    }
}
