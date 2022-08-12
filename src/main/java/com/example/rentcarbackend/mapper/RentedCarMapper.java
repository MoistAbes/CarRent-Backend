package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.domain.RentedCarDto;
import com.example.rentcarbackend.entity.RentedCar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentedCarMapper {

    public RentedCar mapToRentedCar(RentedCarDto rentedCarDto){
        return new RentedCar(
                rentedCarDto.getId(),
                rentedCarDto.getYear(),
                rentedCarDto.getBrand(),
                rentedCarDto.getModel(),
                rentedCarDto.getType()
        );
    }

    public RentedCarDto mapToRentedCarDto(RentedCar rentedCar){
        return new RentedCarDto(
                rentedCar.getId(),
                rentedCar.getYear(),
                rentedCar.getBrand(),
                rentedCar.getModel(),
                rentedCar.getType()
        );
    }

    public List<RentedCarDto> mapToRentedCarDtoList(List<RentedCar> rentedCars){
        return rentedCars.stream()
                .map(this::mapToRentedCarDto)
                .collect(Collectors.toList());
    }

}
