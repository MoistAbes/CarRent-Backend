package com.example.rentcarbackend.controller.facede;

import com.example.rentcarbackend.client.CarClient;
import com.example.rentcarbackend.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CarFacade {

    private final CarClient carClient;

    public List<CarDto> getCars() {
        return carClient.getCars();
    }

    public List<String> getBrands(){
        return carClient.getBrands();
    }

    public List<String> getYears(){
        return carClient.getYears();
    }

    public List<String> getTypes(){
        return carClient.getTypes();
    }

}
