package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.domain.UserDto;
import com.example.rentcarbackend.entity.User;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.mapper.UserMapper;
import com.example.rentcarbackend.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserDbService service;
    private final UserMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {

        //chwilowe
        System.out.println("USER DTO");
        System.out.println("id: " + userDto.getId());
        System.out.println("name: " + userDto.getName());
        System.out.println("lastname: " + userDto.getSurname());


        User user = mapper.mapToUser(userDto);
        service.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException{
        return ResponseEntity.ok(mapper.mapToUserDto(service.getUser(userId)));
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(mapper.mapToUserDtoList(service.getAllUsers()));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        User user = mapper.mapToUser(userDto);
        service.saveUser(user);
        return ResponseEntity.ok().build();
    }


}
