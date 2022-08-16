package com.example.rentcarbackend.client;

import com.example.rentcarbackend.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CarClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarClient.class);

    private final RestTemplate restTemplate;

    @Value("${cars.api.endpoint.prod}")
    private String carsApiEndpoint;
    @Value("${cars.api.key}")
    private String carsApiKey;
    @Value("${cars.api.host}")
    private String carsApiHost;



    public List<CarDto> getCars(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", carsApiKey);
        headers.add("X-RapidAPI-Host", carsApiHost);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        URI url = UriComponentsBuilder.fromHttpUrl(carsApiEndpoint)
                .queryParam("limit", 50)
                .queryParam("page", 0)
                .build()
                .encode()
                .toUri();

        HttpEntity<String> entity = new HttpEntity<>("body", headers);


        try {
            ResponseEntity<CarDto[]> carsResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    CarDto[].class);

            return Optional.ofNullable(carsResponse.getBody())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<String> getBrands(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", carsApiKey);
        headers.add("X-RapidAPI-Host", carsApiHost);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        URI url = UriComponentsBuilder.fromHttpUrl(carsApiEndpoint + "/makes")
                .build()
                .encode()
                .toUri();

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        try {
            ResponseEntity<String[]> brandsResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String[].class);

            return Optional.ofNullable(brandsResponse.getBody())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<String> getYears(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", carsApiKey);
        headers.add("X-RapidAPI-Host", carsApiHost);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        URI url = UriComponentsBuilder.fromHttpUrl(carsApiEndpoint + "/years")
                .build()
                .encode()
                .toUri();

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        try {
            ResponseEntity<String[]> brandsResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String[].class);

            return Optional.ofNullable(brandsResponse.getBody())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<String> getTypes(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", carsApiKey);
        headers.add("X-RapidAPI-Host", carsApiHost);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        URI url = UriComponentsBuilder.fromHttpUrl(carsApiEndpoint + "/types")
                .build()
                .encode()
                .toUri();

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        try {
            ResponseEntity<String[]> brandsResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String[].class);

            return Optional.ofNullable(brandsResponse.getBody())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
