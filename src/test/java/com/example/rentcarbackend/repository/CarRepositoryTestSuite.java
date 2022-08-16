package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.domain.CarRentStatus;
import com.example.rentcarbackend.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CarRepositoryTestSuite {

    @Autowired
    CarRepository repository;

    @Test
    public void findAllTest(){

        //Given
        Car car = new Car(1998, "test brand", "test model", "test type", CarRentStatus.RENTED);
        Car car2 = new Car(2011, "test brand2", "test model2", "test type2", CarRentStatus.RENTED);
        Car car3 = new Car(3002, "test brand3", "test model3", "test type3", CarRentStatus.RENTED);

        Long id = repository.save(car).getId();
        Long id2 = repository.save(car2).getId();
        Long id3 = repository.save(car3).getId();

        //When
        List<Car> resultCars = repository.findAll();

        //Then
        assertEquals(3, resultCars.size());


        //Clean up
        repository.deleteById(id);
        repository.deleteById(id2);
        repository.deleteById(id3);

    }

    @Test
    public void findByIdTest(){

        //Given
        Car car = new Car(1998, "test brand", "test model", "test type", CarRentStatus.RENTED);

        Long id = repository.save(car).getId();

        //When
        Optional<Car> resultCar =  repository.findById(id);

        //Then
        assertEquals(1998, resultCar.get().getYear());
        assertEquals("test brand", resultCar.get().getBrand());
        assertEquals("test model", resultCar.get().getModel());
        assertEquals("test type", resultCar.get().getType());
        assertEquals(CarRentStatus.RENTED, resultCar.get().getStatus());

        //Clean up
        repository.deleteById(id);
    }

    @Test
    public void shouldUpdateCar(){
        //Given
        Car car = new Car(1998, "test brand", "test model", "test type", CarRentStatus.RENTED);

        Long id = repository.save(car).getId();

        //When
        Optional<Car> savedCar =  repository.findById(id);
        savedCar.get().setStatus(CarRentStatus.NOT_RENTED);
        Car updatedCar = repository.save(savedCar.get());


        //Then
        assertEquals(1998, updatedCar.getYear());
        assertEquals("test brand", updatedCar.getBrand());
        assertEquals("test model", updatedCar.getModel());
        assertEquals("test type", updatedCar.getType());
        assertEquals(CarRentStatus.NOT_RENTED, updatedCar.getStatus());

        //Clean up
        repository.deleteById(id);
    }

}
