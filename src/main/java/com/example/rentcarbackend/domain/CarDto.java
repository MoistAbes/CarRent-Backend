package com.example.rentcarbackend.domain;

import lombok.Data;

@Data
public class CarDto {
    private int year;
    private String brand;
    private String model;
    private String type;
}
