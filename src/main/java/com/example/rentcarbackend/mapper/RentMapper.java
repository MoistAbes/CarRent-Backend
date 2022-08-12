package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.domain.RentDto;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.exception.RentedCarNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.service.RentedCarDbService;
import com.example.rentcarbackend.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentMapper {

    private final UserDbService userDbService;
    private final RentedCarDbService rentedCarDbService;


    public Rent mapToRent(final RentDto rentDto) throws UserNotFoundException, RentedCarNotFoundException {

        return new Rent(
                rentDto.getId(),
                userDbService.getUser(rentDto.getUserId()),
                rentedCarDbService.getRentedCar(rentDto.getRentedCarId()),
                rentDto.getRentFrom(),
                rentDto.getRentTo()
        );
    }

    public RentDto mapToRentDto(final Rent rent){
        return new RentDto(
                rent.getId(),
                rent.getUser().getId(),
                rent.getRentedCar().getId(),
                rent.getRentedFrom(),
                rent.getRentedTo()
        );
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rents){
        return rents.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }

}
