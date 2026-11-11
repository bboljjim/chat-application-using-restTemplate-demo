package com.chat.host1.service.impl;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.chat.host1.service.myRestTemplateService;

@Service 
public class myRestTemplateServiceImpl implements myRestTemplateService{

    @Override 
    public boolean requestConnection(){
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/host2/response-connection")
            .encode()
            .build()
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(uri, Boolean.class);
        return responseEntity.getBody();
    }

    @Override 
    public String requestMSG(){
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/host2/response-msg")
            .encode()
            .build()
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();

    }
}
