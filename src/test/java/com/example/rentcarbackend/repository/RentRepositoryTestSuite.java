package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.entity.RentedCar;
import com.example.rentcarbackend.entity.User;
import com.example.rentcarbackend.service.RentDbService;
import com.example.rentcarbackend.service.UserDbService;
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
    RentedCarRepository rentedCarRepository;

    @Test
    public void testShouldSaveRent(){

        //Given
        User user = new User("test name 1", "test surname 1");
        RentedCar rentedCar = new RentedCar(1234, "test brand", "test model", "test type");

        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, rentedCar, rentFrom, rentTo);

        //When
        User savedUser = userRepository.save(user);
        RentedCar savedRentedCar = rentedCarRepository.save(rentedCar);
        Rent savedRent = rentRepository.save(rent);

        //Then

        assertEquals(2022, savedRent.getRentedFrom().getYear());
        assertEquals(5, savedRent.getRentedFrom().getMonth().getValue());
        assertEquals(23, savedRent.getRentedFrom().getDayOfMonth());

        assertEquals(2022, savedRent.getRentedTo().getYear());
        assertEquals(5, savedRent.getRentedTo().getMonth().getValue());
        assertEquals(29, savedRent.getRentedTo().getDayOfMonth());

        assertEquals(1234, savedRentedCar.getYear());

        assertEquals(savedUser.getId(), savedRent.getUser().getId());

        //Clean up
        rentRepository.deleteById(savedRent.getId());
        userRepository.deleteById(savedUser.getId());
        rentedCarRepository.deleteById(savedRentedCar.getId());
    }

    @Test
    public void shouldUpdateRent(){

        //Given
        User user = new User("test name 1", "test surname 1");
        RentedCar rentedCar = new RentedCar(1234, "test brand", "test model", "test type");

        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, rentedCar, rentFrom, rentTo);

        User savedUser = userRepository.save(user);
        RentedCar savedRentedCar = rentedCarRepository.save(rentedCar);
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
        rentedCarRepository.deleteById(savedRentedCar.getId());
    }

    @Test
    public void testShouldDeleteRentNotUser(){

        //Given
        User user = new User("test name 1", "test surname 1");
        RentedCar rentedCar = new RentedCar(1234, "test brand", "test model", "test type");


        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, rentedCar, rentFrom, rentTo);

        User savedUser = userRepository.save(user);
        RentedCar savedRentedCar = rentedCarRepository.save(rentedCar);
        Rent savedRent = rentRepository.save(rent);

        //When
        rentRepository.deleteById(savedRent.getId());

        //Then
        assertTrue(userRepository.existsById(savedUser.getId()));
        assertFalse(rentRepository.existsById(savedRent.getId()));

        //Clean up
        userRepository.deleteById(savedUser.getId());
        rentedCarRepository.deleteById(savedRentedCar.getId());
    }

    @Test
    public void testFullTest(){
        //Given
        User user = new User("test name 1", "test surname 1");
        RentedCar rentedCar = new RentedCar(1234, "test brand", "test model", "test type");

        LocalDate rentFrom = LocalDate.of(2022, 5, 23);
        LocalDate rentTo = LocalDate.of(2022, 5, 29);
        Rent rent = new Rent(user, rentedCar, rentFrom, rentTo);

        User savedUser = userRepository.save(user);
        Rent savedRent = rentRepository.save(rent);
    }

}
