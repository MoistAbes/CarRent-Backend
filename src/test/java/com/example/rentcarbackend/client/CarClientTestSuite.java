package com.example.rentcarbackend.client;

import com.example.rentcarbackend.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarClientTestSuite {

    @Autowired
    CarClient carClient;


    @Test
    void shouldFetchCars() throws InterruptedException {
        //Given
        //When
        TimeUnit.SECONDS.sleep(1);

        List<CarDto> carDtos = carClient.getCars();

        //Then
        assertEquals(50, carDtos.size());
    }

    @Test
    void shouldFetchBrands() throws InterruptedException {

        //Given
        //When
        TimeUnit.SECONDS.sleep(1);

        List<String> brands = carClient.getBrands();

        //Then
        assertFalse(brands.isEmpty());
    }

    @Test
    void shouldFetchTypes() throws InterruptedException {

        //Given
        //When
        TimeUnit.SECONDS.sleep(1);

        List<String> types = carClient.getTypes();

        //Then
        assertFalse(types.isEmpty());
    }

    @Test
    void shouldFetchYears() throws InterruptedException {

        //Given
        //When
        TimeUnit.SECONDS.sleep(1);
        List<String> years = carClient.getYears();

        //Then
        assertFalse(years.isEmpty());
    }



}
