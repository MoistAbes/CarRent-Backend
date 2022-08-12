package com.example.rentcarbackend.service;

import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.entity.RentedCar;
import com.example.rentcarbackend.exception.RentNotFoundException;
import com.example.rentcarbackend.exception.RentedCarNotFoundException;
import com.example.rentcarbackend.repository.RentedCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentedCarDbService {

    private final RentedCarRepository repository;

    public List<RentedCar> getAllRentedCars(){
        return repository.findAll();
    }

    public RentedCar getRentedCar(final Long rentedCarId) throws RentedCarNotFoundException {
        return repository.findById(rentedCarId).orElseThrow(RentedCarNotFoundException::new);
    }

    public RentedCar saveRentedCar(final RentedCar rentedCar){
        return repository.save(rentedCar);
    }

    public void deleteRent(Long rentedCarId){
        repository.deleteById(rentedCarId);
    }

}
