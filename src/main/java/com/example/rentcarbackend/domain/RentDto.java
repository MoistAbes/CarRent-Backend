package com.example.rentcarbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentDto {

    private Long id;
    private Long userId;
    private int year;
    private String brand;
    private String model;
    private String type;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    @Override
    public String toString() {
        return "RentDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", year=" + year +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", rentFrom=" + rentFrom +
                ", rentTo=" + rentTo +
                '}';
    }
}
