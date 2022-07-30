package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.domain.RentDto;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.exception.RentNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.mapper.RentMapper;
import com.example.rentcarbackend.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/rents")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RentController {

    private final RentDbService service;
    private final RentMapper mapper;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents(){
        List<Rent> rents = service.getAllRents();
        return ResponseEntity.ok(mapper.mapToRentDtoList(rents));
    }

    @GetMapping(value = "{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws RentNotFoundException {
        return ResponseEntity.ok(mapper.mapToRentDto(service.getRent(rentId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRent(@RequestBody RentDto rentDto) throws UserNotFoundException {
        Rent rent = mapper.mapToRent(rentDto);
        service.saveRent(rent);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto) throws UserNotFoundException {
        Rent rent = mapper.mapToRent(rentDto);
        Rent updatedRent = service.saveRent(rent);
        return ResponseEntity.ok(mapper.mapToRentDto(updatedRent));
    }

    @DeleteMapping(value = "{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId){
        service.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }




}
