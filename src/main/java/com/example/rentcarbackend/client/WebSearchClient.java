package com.example.rentcarbackend.client;

import com.example.rentcarbackend.domain.CarReviewDto;
import com.example.rentcarbackend.domain.WebValueDto;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WebSearchClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarClient.class);

    private final RestTemplate restTemplate;

    @Value("${web.api.endpoint.prod}")
    private String webApiEndpoint;
    @Value("${web.api.key}")
    private String webApiKey;
    @Value("${web.api.host}")
    private String webApiHost;


    public CarReviewDto getReview(String search){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", webApiKey);
        headers.add("X-RapidAPI-Host", webApiHost);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        URI url = UriComponentsBuilder.fromHttpUrl(webApiEndpoint)
                .queryParam("q", search)
                .queryParam("pageNumber", 1)
                .queryParam("pageSize", 1)
                .queryParam("autoCorrect", true)
                .build()
                .encode()
                .toUri();

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        try {
            ResponseEntity<WebValueDto> webResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    WebValueDto.class);

            List<WebValueDto> valueDtos = Optional.ofNullable(webResponse.getBody())
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

            //troche dziwnie to dziala jak bedzie czas to pomyslec nad tym XD
            return valueDtos.get(0).getReview().get(0);

        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new CarReviewDto();
        }
    }


}
