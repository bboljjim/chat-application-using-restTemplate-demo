package com.chat.host2.service.impl;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.chat.host2.service.RestTemplateService;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    @Override 
    public String requestMSG(){
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:8080")
            .path("/host1/response-msg")
            .encode()
            .build()
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }
}
