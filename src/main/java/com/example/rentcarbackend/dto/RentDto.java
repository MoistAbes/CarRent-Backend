package com.example.rentcarbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentDto {

    private Long id;
    private Long userId;
    private Long rentedCarId;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    @Override
    public String toString() {
        return "RentDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", rentFrom=" + rentFrom +
                ", rentTo=" + rentTo +
                '}';
    }
}
