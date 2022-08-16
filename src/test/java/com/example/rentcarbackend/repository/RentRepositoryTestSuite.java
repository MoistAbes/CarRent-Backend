package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.domain.CarRentStatus;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.entity.Car;
import com.example.rentcarbackend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentRepositoryTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    CarRepository carRepository;

    @Test
    public void testShouldSaveRent(){

        //Given
        User user = new User("test name 1", "test surname 1");
        Car car = new Car(1234, "test brand", "test model", "test type", CarRentStatus.RENTED);

        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, car, rentFrom, rentTo);

        //When
        User savedUser = userRepository.save(user);
        Car savedCar = carRepository.save(car);
        Rent savedRent = rentRepository.save(rent);

        //Then

        assertEquals(2022, savedRent.getRentedFrom().getYear());
        assertEquals(5, savedRent.getRentedFrom().getMonth().getValue());
        assertEquals(23, savedRent.getRentedFrom().getDayOfMonth());

        assertEquals(2022, savedRent.getRentedTo().getYear());
        assertEquals(5, savedRent.getRentedTo().getMonth().getValue());
        assertEquals(29, savedRent.getRentedTo().getDayOfMonth());

        assertEquals(1234, savedCar.getYear());

        assertEquals(savedUser.getId(), savedRent.getUser().getId());

        //Clean up
        rentRepository.deleteById(savedRent.getId());
        userRepository.deleteById(savedUser.getId());
        carRepository.deleteById(savedCar.getId());
    }

    @Test
    public void shouldUpdateRent(){

        //Given
        User user = new User("test name 1", "test surname 1");
        Car car = new Car(1234, "test brand", "test model", "test type", CarRentStatus.RENTED);

        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, car, rentFrom, rentTo);

        User savedUser = userRepository.save(user);
        Car savedCar = carRepository.save(car);
        rentRepository.save(rent);

        //When
        LocalDate updatedRentFrom = LocalDate.of(2022, 5, 13);
        LocalDate updatedRentTo = LocalDate.of(2022, 5, 17);

        rent.setRentedFrom(updatedRentFrom);
        rent.setRentedTo(updatedRentTo);

        Rent updatedRent = rentRepository.save(rent);

        //Then
        assertEquals(13, updatedRent.getRentedFrom().getDayOfMonth());
        assertEquals(17, updatedRent.getRentedTo().getDayOfMonth());

        //Clean up
        rentRepository.deleteById(updatedRent.getId());
        userRepository.deleteById(savedUser.getId());
        carRepository.deleteById(savedCar.getId());
    }

    @Test
    public void testShouldDeleteRentNotUser(){

        //Given
        User user = new User("test name 1", "test surname 1");
        Car car = new Car(1234, "test brand", "test model", "test type", CarRentStatus.RENTED);


        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, car, rentFrom, rentTo);

        User savedUser = userRepository.save(user);
        Car savedCar = carRepository.save(car);
        Rent savedRent = rentRepository.save(rent);

        //When
        rentRepository.deleteById(savedRent.getId());

        //Then
        assertTrue(userRepository.existsById(savedUser.getId()));
        assertFalse(rentRepository.existsById(savedRent.getId()));

        //Clean up
        userRepository.deleteById(savedUser.getId());
        carRepository.deleteById(savedCar.getId());
    }

    @Test
    public void testShouldDeleteRentNotCar(){

        //Given
        User user = new User("test name 1", "test surname 1");
        Car car = new Car(1234, "test brand", "test model", "test type", CarRentStatus.RENTED);


        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, car, rentFrom, rentTo);

        User savedUser = userRepository.save(user);
        Car savedCar = carRepository.save(car);
        Rent savedRent = rentRepository.save(rent);

        //When
        rentRepository.deleteById(savedRent.getId());

        //Then
        assertTrue(carRepository.existsById(savedCar.getId()));
        assertFalse(rentRepository.existsById(savedRent.getId()));

        //Clean up
        userRepository.deleteById(savedUser.getId());
        carRepository.deleteById(savedCar.getId());
    }


}
