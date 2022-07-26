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
@RequestMapping("v1/car")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CarController {

    private final CarClient carClient;

    @GetMapping("cars")
    public void getCars() {
        List<CarDto> cars = carClient.getCars();

        System.out.println(cars);


    }

}
