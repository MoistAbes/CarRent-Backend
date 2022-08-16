package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.LoginInfoFacade;
import com.example.rentcarbackend.dto.LoginInfoDto;
import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.exception.LoginInfoNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.mapper.LoginInfoMapper;
import com.example.rentcarbackend.service.LoginInfoDbService;
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


    private final LoginInfoFacade loginInfoFacade;

    @GetMapping
    public ResponseEntity<List<LoginInfoDto>> getLoginInfos(){
        return ResponseEntity.ok(loginInfoFacade.getLoginInfos());
    }

    @GetMapping(value = "/logininfo/{loginInfoId}")
    public ResponseEntity<LoginInfoDto> getLoginInfo(@PathVariable Long loginInfoId) throws LoginInfoNotFoundException {
        return ResponseEntity.ok(loginInfoFacade.getLoginInfo(loginInfoId));
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<LoginInfoDto>> getUsersLoginInfos(@PathVariable Long userId) throws LoginInfoNotFoundException {
        return ResponseEntity.ok(loginInfoFacade.getUsersLoginInfos(userId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginInfoDto> createLoginInfo(@RequestBody LoginInfoDto loginInfoDto) throws UserNotFoundException {
        return ResponseEntity.ok(loginInfoFacade.createLoginInfo(loginInfoDto));

    }


}
