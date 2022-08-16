package com.example.rentcarbackend.service;

import com.example.rentcarbackend.entity.Car;
import com.example.rentcarbackend.exception.RentedCarNotFoundException;
import com.example.rentcarbackend.repository.RentedCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentedCarDbService {

    private final RentedCarRepository repository;

    public List<Car> getAllRentedCars(){
        return repository.findAll();
    }

    public Car getRentedCar(final Long rentedCarId) throws RentedCarNotFoundException {
        return repository.findById(rentedCarId).orElseThrow(RentedCarNotFoundException::new);
    }

    public Car saveRentedCar(final Car car){
        return repository.save(car);
    }

    public void deleteRent(Long rentedCarId){
        repository.deleteById(rentedCarId);
    }

}
