package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.domain.LoginInfoDto;
import com.example.rentcarbackend.domain.RentDto;
import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.entity.Rent;
import com.example.rentcarbackend.exception.LoginInfoNotFoundException;
import com.example.rentcarbackend.exception.RentNotFoundException;
import com.example.rentcarbackend.exception.RentedCarNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.mapper.LoginInfoMapper;
import com.example.rentcarbackend.mapper.RentMapper;
import com.example.rentcarbackend.service.LoginInfoDbService;
import com.example.rentcarbackend.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/loginInfo")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginInfoController {


    private final LoginInfoDbService service;
    private final LoginInfoMapper mapper;

    @GetMapping
    public ResponseEntity<List<LoginInfoDto>> getLoginInfos(){
        List<LoginInfo> loginInfos = service.getAllLoginInfos();
        return ResponseEntity.ok(mapper.mapToLoginInfoDtoList(loginInfos));
    }

    @GetMapping(value = "{loginInfoId}")
    public ResponseEntity<LoginInfoDto> getLoginInfo(@PathVariable Long loginInfoId) throws LoginInfoNotFoundException {
        return ResponseEntity.ok(mapper.mapToLoginInfoDto(service.getLoginInfo(loginInfoId)));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<List<LoginInfoDto>> getUsersLoginInfos(@PathVariable Long userId) throws LoginInfoNotFoundException {
        return ResponseEntity.ok(mapper.mapToLoginInfoDtoList(service.getAllUserLoginInfos(userId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createLoginInfo(@RequestBody LoginInfoDto loginInfoDto) throws UserNotFoundException {
        LoginInfo loginInfo = mapper.mapToLoginInfo(loginInfoDto);
        service.saveLoginInfo(loginInfo);
        return ResponseEntity.ok().build();
    }


}
