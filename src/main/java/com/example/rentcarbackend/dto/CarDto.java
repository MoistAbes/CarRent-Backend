package com.example.rentcarbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarDto {
    @JsonProperty("year")
    private int year;

    @JsonProperty("make")
    private String brand;

    @JsonProperty("model")
    private String model;

    @JsonProperty("type")
    private String type;
}
