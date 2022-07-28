package com.example.rentcarbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Car {

    private int year;

    private String brand;

    private String model;

    private String type;
}
