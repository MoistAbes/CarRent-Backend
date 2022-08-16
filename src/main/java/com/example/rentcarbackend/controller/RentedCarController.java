package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.RentedCarFacade;
import com.example.rentcarbackend.dto.RentedCarDto;
import com.example.rentcarbackend.entity.Car;
import com.example.rentcarbackend.exception.CarNotFoundException;
import com.example.rentcarbackend.mapper.RentedCarMapper;
import com.example.rentcarbackend.service.CarDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/rentedCars")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RentedCarController {


    private final RentedCarFacade rentedCarFacade;

    @GetMapping
    public ResponseEntity<List<RentedCarDto>> getRenterCars(){
        return ResponseEntity.ok(rentedCarFacade.getAllCars());
    }

    @GetMapping(value = "{rentedCarId}")
    public ResponseEntity<RentedCarDto> getRentedCar(@PathVariable Long rentedCarId) throws CarNotFoundException {
        return ResponseEntity.ok(rentedCarFacade.getRentedCar(rentedCarId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createRentedCar(@RequestBody RentedCarDto rentedCarDto){
        return ResponseEntity.ok(rentedCarFacade.createRentedCar(rentedCarDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentedCarDto> updateRentedCarStatus(@RequestBody RentedCarDto rentedCarDto) {
       return ResponseEntity.ok(rentedCarFacade.updateRentedCarStatus(rentedCarDto));
    }

}
