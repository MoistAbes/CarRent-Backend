package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.domain.CarRentStatus;
import com.example.rentcarbackend.dto.RentedCarDto;
import com.example.rentcarbackend.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentedCarMapperTestSuite {

    @Autowired
    RentedCarMapper mapper;


    @Test
    void shouldMapToCar(){
        //Given
        RentedCarDto rentedCarDto = new RentedCarDto(
                1L,
                1992,
                "test brand",
                "test model",
                "test type",
                CarRentStatus.RENTED
        );

        //When
        Car mappedCar = mapper.mapToRentedCar(rentedCarDto);

        //Then
        assertEquals(1992, mappedCar.getYear());
        assertEquals("test brand", mappedCar.getBrand());
        assertEquals("test model", mappedCar.getModel());
        assertEquals("test type", mappedCar.getType());
        assertEquals(CarRentStatus.RENTED, mappedCar.getStatus());

    }

    @Test
    void shouldMapToRentedCarDto(){
        //Given
        Car car = new Car(
                1L,
                1992,
                "test brand",
                "test model",
                "test type",
                CarRentStatus.RENTED
        );

        //When
        RentedCarDto mappedRentedCarDto = mapper.mapToRentedCarDto(car);

        //Then
        assertEquals(1992, mappedRentedCarDto.getYear());
        assertEquals("test brand", mappedRentedCarDto.getBrand());
        assertEquals("test model", mappedRentedCarDto.getModel());
        assertEquals("test type", mappedRentedCarDto.getType());
        assertEquals(CarRentStatus.RENTED, mappedRentedCarDto.getStatus());

    }

    @Test
    void shouldMapToRentedCarDtoList(){
        //Given
        Car car = new Car(
                1L,
                1992,
                "test brand",
                "test model",
                "test type",
                CarRentStatus.RENTED
        );

        Car car2 = new Car(
                2L,
                1992,
                "test brand2",
                "test model2",
                "test type2",
                CarRentStatus.RENTED
        );
        Car car3 = new Car(
                2L,
                1992,
                "test brand3",
                "test model3",
                "test type3",
                CarRentStatus.RENTED
        );

        List<Car> cars = List.of(car, car2, car3);

        //When
        List<RentedCarDto> mappedRentedCarDtoList = mapper.mapToRentedCarDtoList(cars);


        //Then
        for (int i = 0; i < mappedRentedCarDtoList.size(); i++){
            assertEquals(cars.get(i).getYear(), mappedRentedCarDtoList.get(i).getYear());
            assertEquals(cars.get(i).getBrand(), mappedRentedCarDtoList.get(i).getBrand());
            assertEquals(cars.get(i).getModel(), mappedRentedCarDtoList.get(i).getModel());
            assertEquals(cars.get(i).getType(), mappedRentedCarDtoList.get(i).getType());
            assertEquals(cars.get(i).getStatus(), mappedRentedCarDtoList.get(i).getStatus());

        }

    }

}
