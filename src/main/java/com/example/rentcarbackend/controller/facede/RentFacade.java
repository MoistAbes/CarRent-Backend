package com.example.rentcarbackend.controller.facede;

import com.example.rentcarbackend.dto.RentDto;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.exception.CarNotFoundException;
import com.example.rentcarbackend.exception.RentNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.mapper.RentMapper;
import com.example.rentcarbackend.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentFacade {

    private final RentDbService service;
    private final RentMapper mapper;

    public List<RentDto> getRents(){
        List<Rent> rents = service.getAllRents();
        return mapper.mapToRentDtoList(rents);
    }

    public RentDto getRent(Long rentId) throws RentNotFoundException {
        return mapper.mapToRentDto(service.getRent(rentId));
    }

    public List<RentDto> getAllUserRents(Long userId){
        return mapper.mapToRentDtoList(service.getAllUserRents(userId));
    }

    public RentDto createRent(RentDto rentDto) throws UserNotFoundException, CarNotFoundException {
        Rent rent = mapper.mapToRent(rentDto);
        return mapper.mapToRentDto(service.saveRent(rent));
    }

    public RentDto updateRent(RentDto rentDto) throws UserNotFoundException, CarNotFoundException {
        Rent rent = mapper.mapToRent(rentDto);
        Rent updatedRent = service.saveRent(rent);
        return mapper.mapToRentDto(updatedRent);
    }

    public void deleteRent(Long rentId){
        service.deleteRent(rentId);
    }

}
