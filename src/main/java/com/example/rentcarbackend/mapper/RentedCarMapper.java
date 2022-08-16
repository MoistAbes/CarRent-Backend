package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.dto.RentedCarDto;
import com.example.rentcarbackend.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentedCarMapper {

    public Car mapToRentedCar(RentedCarDto rentedCarDto){
        return new Car(
                rentedCarDto.getId(),
                rentedCarDto.getYear(),
                rentedCarDto.getBrand(),
                rentedCarDto.getModel(),
                rentedCarDto.getType(),
                rentedCarDto.getStatus()
        );
    }

    public RentedCarDto mapToRentedCarDto(Car car){
        return new RentedCarDto(
                car.getId(),
                car.getYear(),
                car.getBrand(),
                car.getModel(),
                car.getType(),
                car.getStatus()
        );
    }

    public List<RentedCarDto> mapToRentedCarDtoList(List<Car> cars){
        return cars.stream()
                .map(this::mapToRentedCarDto)
                .collect(Collectors.toList());
    }

}
