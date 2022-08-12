package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.domain.UserDto;
import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.entity.User;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.exception.UsernameIsTakenException;
import com.example.rentcarbackend.mapper.UserMapper;
import com.example.rentcarbackend.service.LoginInfoDbService;
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
    private final LoginInfoDbService loginInfoDbService;
    private final UserMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) throws UsernameIsTakenException {
        if  (checkIfUsernameTaken(userDto.getUsername())) {
            User user = mapper.mapToUser(userDto);
            service.saveUser(user);
            return ResponseEntity.ok().build();
        }else {
            throw new UsernameIsTakenException();
        }
    }

    @GetMapping(value = "{username}/{password}")
    public ResponseEntity<UserDto> getAuthentication(@PathVariable String username, @PathVariable String password){
        User authenticatedUser = service.getAuthentication(username, password);
        if (authenticatedUser.getId() != null){
            loginInfoDbService.saveLoginInfo(new LoginInfo(authenticatedUser));
        }
        return ResponseEntity.ok(mapper.mapToUserDto(authenticatedUser));
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

    private boolean checkIfUsernameTaken(String username){
        long count = service.getAllUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .count();

        return count <= 0;
    }


}
