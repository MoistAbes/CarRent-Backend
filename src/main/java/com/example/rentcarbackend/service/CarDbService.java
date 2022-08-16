package com.example.rentcarbackend.service;

import com.example.rentcarbackend.entity.Car;
import com.example.rentcarbackend.exception.CarNotFoundException;
import com.example.rentcarbackend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarDbService {

    private final CarRepository repository;

    public List<Car> getAllRentedCars(){
        return repository.findAll();
    }

    public Car getRentedCar(final Long rentedCarId) throws CarNotFoundException {
        return repository.findById(rentedCarId).orElseThrow(CarNotFoundException::new);
    }

    public Car saveRentedCar(final Car car){
        return repository.save(car);
    }

    public void deleteRentedCar(Long rentedCarId){
        repository.deleteById(rentedCarId);
    }

}
