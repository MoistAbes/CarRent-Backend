package com.example.rentcarbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
