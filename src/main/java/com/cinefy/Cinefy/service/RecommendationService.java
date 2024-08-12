package com.cinefy.Cinefy.service;

import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Service
public class RecommendationService {
    private static final String PYTHON_API_URL = "http://127.0.0.1:5000/recommend";

    public List<Map<String, Object>> getRecommendations(List<String> inputMovies){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, List<String>>requestBody = Map.of("movies", inputMovies);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, List<String>>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map[]> response = restTemplate.exchange(
                PYTHON_API_URL, HttpMethod.POST, entity, Map[].class
        );
        return Arrays.asList(response.getBody());
    }
}
