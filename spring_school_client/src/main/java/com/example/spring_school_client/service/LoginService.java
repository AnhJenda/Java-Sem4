package com.example.spring_school_client.service;

import com.example.spring_school_client.config.ServiceProperties;
import com.example.spring_school_client.dto.ClassDto;
import com.example.spring_school_client.dto.LoginResponseDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 8/3/2023
    Project: Spring_school_client
*/
@Component
public class LoginService {
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ServiceProperties serviceProperties;

    public LoginResponseDto getListClassroom(ClassDto criteria){
        String url =  serviceProperties.getBaseUrl() + "/login";

        ParameterizedTypeReference<LoginResponseDto> responseType = new ParameterizedTypeReference<LoginResponseDto>() {};

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json"); // set header
        httpHeaders.add("Authorization", "Bearer + token"); // set token

        HttpEntity<ClassDto> entity = new HttpEntity<>(criteria, httpHeaders);

        ResponseEntity<LoginResponseDto> response = restTemplate.exchange(url, HttpMethod.POST,entity, responseType);
        return response.getBody();
    }
}
