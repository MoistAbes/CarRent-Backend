package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.client.CarClient;
import com.example.rentcarbackend.controller.facede.CarFacade;
import com.example.rentcarbackend.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final CarFacade carFacade;

    @GetMapping()
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(carFacade.getCars());
    }

    @GetMapping("brands")
    public ResponseEntity<List<String>> getBrands(){
        return ResponseEntity.ok(carFacade.getBrands());
    }

    @GetMapping("years")
    public ResponseEntity<List<String>> getYears(){
        return ResponseEntity.ok(carFacade.getYears());
    }

    @GetMapping("types")
    public ResponseEntity<List<String>> getTypes(){
        return ResponseEntity.ok(carFacade.getTypes());
    }

}
