package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.client.WebSearchClient;
import com.example.rentcarbackend.domain.CarReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/review")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WebSearchController {

    private final WebSearchClient webSearchClient;

    @GetMapping(value = "{search}")
    public CarReviewDto getSearch(@PathVariable String search){
        return webSearchClient.getReview(search);
    }

}
