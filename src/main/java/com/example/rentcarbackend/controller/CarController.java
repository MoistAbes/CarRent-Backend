package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.client.CarClient;
import com.example.rentcarbackend.domain.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/cars")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CarController {

    private final CarClient carClient;

    @GetMapping()
    public List<CarDto> getCars() {
        return carClient.getCars();
    }

    @GetMapping("brands")
    public List<String> getBrands(){
        return carClient.getBrands();
    }

    @GetMapping("years")
    public List<String> getYears(){
        return carClient.getYears();
    }

    @GetMapping("types")
    public List<String> getTypes(){
        return carClient.getTypes();
    }

}
