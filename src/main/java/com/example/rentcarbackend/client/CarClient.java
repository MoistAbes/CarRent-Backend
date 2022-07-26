package com.example.rentcarbackend.client;

import com.example.rentcarbackend.domain.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CarClient {
    private final RestTemplate restTemplate;

    @Value("${cars.api.endpoint.prod}")
    private String carsApiEndpoint;
    @Value("${cars.api.key}")
    private String carsApiKey;
    @Value("${cars.api.host}")
    private String carsApiHost;



    public List<CarDto> getCars(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", carsApiKey);
        headers.add("X-RapidAPI-Host", carsApiHost);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        URI url = UriComponentsBuilder.fromHttpUrl(carsApiEndpoint)
                .queryParam("limit", 10)
                .queryParam("page", 0)
                .build()
                .encode()
                .toUri();

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<CarDto[]> carsResponse = restTemplate.exchange(url, HttpMethod.GET, entity, CarDto[].class);


        return Optional.ofNullable(carsResponse.getBody())
                        .map(Arrays::asList)
                        .orElse(Collections.emptyList());

    }
}
