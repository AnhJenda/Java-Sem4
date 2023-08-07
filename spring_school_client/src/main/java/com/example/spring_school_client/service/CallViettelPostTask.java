package com.example.spring_school_client.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

/*
    @author: Dinh Quang Anh
    Date   : 8/2/2023
    Project: Spring_school_client
*/
public class CallViettelPostTask implements Callable<String> {
    private String url = "https://partner.viettelpost.vn/v2/categories/listDistrict?provinceId=2";
    private RestTemplate restTemplate;

    public CallViettelPostTask(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public String call() throws Exception {
        long start_time = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        System.out.println("time to execute subtask : " + (System.currentTimeMillis()-start_time));
        return response.getBody();
    }
}
