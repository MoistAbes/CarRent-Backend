package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.RentFacade;
import com.example.rentcarbackend.dto.RentDto;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.exception.RentNotFoundException;
import com.example.rentcarbackend.exception.CarNotFoundException;
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

    private final RentFacade rentFacade;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents(){
        return ResponseEntity.ok(rentFacade.getRents());
    }

    @GetMapping(value = "/rent/{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws RentNotFoundException {
        return ResponseEntity.ok(rentFacade.getRent(rentId));
    }

    @GetMapping(value = "/userRents/{userId}")
    public ResponseEntity<List<RentDto>> getAllUserRents(@PathVariable Long userId){
        return ResponseEntity.ok(rentFacade.getAllUserRents(userId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentDto> createRent(@RequestBody RentDto rentDto) throws UserNotFoundException, CarNotFoundException {
       return ResponseEntity.ok(rentFacade.createRent(rentDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto) throws UserNotFoundException, CarNotFoundException {
       return ResponseEntity.ok(rentFacade.updateRent(rentDto));
    }

    @DeleteMapping(value = "{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId){
        rentFacade.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }

}
