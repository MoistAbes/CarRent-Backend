package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.domain.CarRentStatus;
import com.example.rentcarbackend.dto.RentDto;
import com.example.rentcarbackend.dto.RentedCarDto;
import com.example.rentcarbackend.dto.UserDto;
import com.example.rentcarbackend.entity.Car;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.entity.User;
import com.example.rentcarbackend.exception.CarNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.service.CarDbService;
import com.example.rentcarbackend.service.RentDbService;
import com.example.rentcarbackend.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentMapperTestSuite {

    @Autowired
    RentMapper mapper;

    @Autowired
    UserDbService userDbService;

    @Autowired
    CarDbService carDbService;

    @Autowired
    RentDbService rentDbService;

    @Test
    void shouldMapToRentDto() throws UserNotFoundException, CarNotFoundException {

        //Given
        User user = new User(
                "test",
                "test",
                "test",
                "test"
        );

        Car car = new Car(
                1992,
                "test",
                "test",
                "test",
                CarRentStatus.RENTED
        );

        Long userId = userDbService.saveUser(user).getId();
        Long carId = carDbService.saveRentedCar(car).getId();

        Rent rent = new Rent(
                userDbService.getUser(userId),
                carDbService.getRentedCar(carId),
                LocalDate.of(1992, 5, 25),
                LocalDate.of(1992, 5, 28)
        );

        //When
        RentDto rentDto = mapper.mapToRentDto(rent);

        //Then
        assertEquals(userId, rentDto.getUserId());
        assertEquals(carId, rentDto.getRentedCarId());

        //clean up
        userDbService.deleteUser(userId);
        carDbService.deleteRentedCar(carId);
    }

    @Test
    void shouldMapToRent() throws UserNotFoundException, CarNotFoundException {
        //Given
        User user = new User(
                "test",
                "test",
                "test",
                "test"
        );

        Car car = new Car(
                1992,
                "test",
                "test",
                "test",
                CarRentStatus.RENTED
        );

        Long userId = userDbService.saveUser(user).getId();
        Long carId = carDbService.saveRentedCar(car).getId();

        RentDto rentDto = new RentDto(
                1L,
                userId,
                carId,
                LocalDate.of(1992, 5, 25),
                LocalDate.of(1992, 5, 28)
        );

        //When
        Rent rent = mapper.mapToRent(rentDto);

        //Then
        assertEquals(userId, rentDto.getUserId());
        assertEquals(carId, rentDto.getRentedCarId());

        //clean up
        userDbService.deleteUser(userId);
        carDbService.deleteRentedCar(carId);


    }

    @Test
    void shouldMapToRentDtoList() throws CarNotFoundException, UserNotFoundException {
        //Given
        //Given
        User user = new User(
                "test",
                "test",
                "test",
                "test"
        );

        Car car = new Car(
                1992,
                "test",
                "test",
                "test",
                CarRentStatus.RENTED
        );

        Long userId = userDbService.saveUser(user).getId();
        Long carId = carDbService.saveRentedCar(car).getId();

        Rent rent = new Rent(
                1L,
                userDbService.getUser(userId),
                carDbService.getRentedCar(carId),
                LocalDate.of(1992, 5, 25),
                LocalDate.of(1992, 5, 28)
        );
        Rent rent2 = new Rent(
                2L,
                userDbService.getUser(userId),
                carDbService.getRentedCar(carId),
                LocalDate.of(1992, 5, 25),
                LocalDate.of(1992, 5, 28)
        );
        Rent rent3 = new Rent(
                3L,
                userDbService.getUser(userId),
                carDbService.getRentedCar(carId),
                LocalDate.of(1992, 5, 25),
                LocalDate.of(1992, 5, 28)
        );

        List<Rent> rents = List.of(rent, rent2, rent3);

        //When
        List<RentDto> mappedRentsDto = mapper.mapToRentDtoList(rents);


        //Then
        for (RentDto rentDto : mappedRentsDto) {
            assertEquals(userId, rentDto.getUserId());
            assertEquals(carId, rentDto.getRentedCarId());
        }

        //clean up
        userDbService.deleteUser(userId);
        carDbService.deleteRentedCar(carId);

    }
}
