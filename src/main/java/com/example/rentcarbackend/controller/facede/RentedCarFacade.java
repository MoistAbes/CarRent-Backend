package com.example.rentcarbackend.controller.facede;


import com.example.rentcarbackend.dto.RentedCarDto;
import com.example.rentcarbackend.entity.Car;
import com.example.rentcarbackend.exception.CarNotFoundException;
import com.example.rentcarbackend.mapper.RentedCarMapper;
import com.example.rentcarbackend.mapper.UserMapper;
import com.example.rentcarbackend.service.CarDbService;
import com.example.rentcarbackend.service.LoginInfoDbService;
import com.example.rentcarbackend.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentedCarFacade {

    private final RentedCarMapper mapper;
    private final CarDbService service;


    public List<RentedCarDto> getAllCars(){
        List<Car> cars = service.getAllRentedCars();
        return mapper.mapToRentedCarDtoList(cars);
    }

    public RentedCarDto getRentedCar(Long rentedCarId) throws CarNotFoundException {
        Car car = service.getRentedCar(rentedCarId);
        return mapper.mapToRentedCarDto(car);
    }

    public Long createRentedCar(RentedCarDto rentedCarDto){
        Car car = mapper.mapToRentedCar(rentedCarDto);
        Car savedCar = service.saveRentedCar(car);
        return savedCar.getId();
    }

    public RentedCarDto updateRentedCarStatus(RentedCarDto rentedCarDto){
        Car car = mapper.mapToRentedCar(rentedCarDto);
        Car updatedRentedCar = service.saveRentedCar(car);
        return mapper.mapToRentedCarDto(updatedRentedCar);
    }

}
