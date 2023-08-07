package com.example.spring_school_client.service;

import com.example.spring_school_client.dto.ClassDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
    @author: Dinh Quang Anh
    Date   : 8/2/2023
    Project: Spring_school_client
*/
public class Test implements Runnable{
    private String url = "https://partner.viettelpost.vn/v2/categories/listDistrict?provinceId=2";


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        int m = 10;
        // not use multi-threading
        Test obj = new Test();
        for (int i = 0; i < m; i++){
            obj.gets();
        }
        // use multi-threading
        for (int i = 1; i <= m; i++) {
            Test callApi = new Test();
            Thread thread = new Thread(callApi);
            thread.start();
//            System.err.println("result = " + runableDemo.result);
        }

        ExecutorService service= Executors.newFixedThreadPool(m);
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 1; i <= m; i++){
            CallViettelPostTask task = new CallViettelPostTask(obj.url, restTemplate);
            Future<String> f = service.submit(task);
            futures.add(f);
        }
        futures.parallelStream().forEach(item -> {
            try {
                System.out.println(item.get());
            } catch (InterruptedException e){
                throw new RuntimeException();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private String gets(){
        long start_time = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return response.getBody();

    }

    @Override
    public void run() {
        gets();
    }
}
