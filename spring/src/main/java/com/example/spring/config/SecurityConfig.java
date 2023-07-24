package com.example.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
    @author: Dinh Quang Anh
    Date   : 7/21/2023
    Project: spring
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf().ignoringAntMatchers("/api/v1/login");
        // antMatchers: xem đường dẫn có giống với đối số ko
        httpSecurity.authorizeRequests().antMatchers("/api/v1/login**").permitAll();

    }
}
