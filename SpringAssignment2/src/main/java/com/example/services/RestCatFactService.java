package com.example.services;

import org.springframework.stereotype.Component;

import com.example.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public CatFact getFact() {
        // TODO Auto-generated method stub
        return restTemplate.getForObject("https://cat-data.netlify.app/api/facts/random", CatFact.class);
    }

}

