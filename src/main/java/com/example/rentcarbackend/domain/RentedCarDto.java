package com.example.rentcarbackend.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class RentedCarDto {

    private long id;
    private int year;
    private String brand;
    private String model;
    private String type;



}
