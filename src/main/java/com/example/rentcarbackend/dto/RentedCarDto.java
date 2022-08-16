package com.example.rentcarbackend.dto;

import com.example.rentcarbackend.domain.CarRentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class RentedCarDto {

    private long id;
    private int year;
    private String brand;
    private String model;
    private String type;
    private CarRentStatus status;



}
