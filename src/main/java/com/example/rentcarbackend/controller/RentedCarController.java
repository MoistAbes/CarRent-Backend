package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.domain.RentDto;
import com.example.rentcarbackend.domain.RentedCarDto;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.entity.RentedCar;
import com.example.rentcarbackend.exception.RentedCarNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.mapper.RentedCarMapper;
import com.example.rentcarbackend.service.RentedCarDbService;
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


    private final RentedCarDbService service;
    private final RentedCarMapper mapper;

    @GetMapping
    public ResponseEntity<List<RentedCarDto>> getRenterCars(){
        List<RentedCar> rentedCars = service.getAllRentedCars();
        return ResponseEntity.ok(mapper.mapToRentedCarDtoList(rentedCars));
    }

    @GetMapping(value = "{rentedCarId}")
    public ResponseEntity<RentedCarDto> getRentedCar(@PathVariable Long rentedCarId) throws RentedCarNotFoundException {
        return ResponseEntity.ok(mapper.mapToRentedCarDto(service.getRentedCar(rentedCarId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createRentedCar(@RequestBody RentedCarDto rentedCarDto){
        RentedCar rentedCar = mapper.mapToRentedCar(rentedCarDto);
        RentedCar savedRentedCar = service.saveRentedCar(rentedCar);
        return ResponseEntity.ok(savedRentedCar.getId());
    }

}
