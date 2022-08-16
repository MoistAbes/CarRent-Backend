package com.example.rentcarbackend.controller.facede;

import com.example.rentcarbackend.dto.LoginInfoDto;
import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.exception.LoginInfoNotFoundException;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.mapper.LoginInfoMapper;
import com.example.rentcarbackend.service.LoginInfoDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginInfoFacade {

    private final LoginInfoDbService service;
    private final LoginInfoMapper mapper;

    public List<LoginInfoDto> getLoginInfos(){
        List<LoginInfo> loginInfos = service.getAllLoginInfos();
        return mapper.mapToLoginInfoDtoList(loginInfos);
    }

    public LoginInfoDto getLoginInfo(Long loginInfoId) throws LoginInfoNotFoundException {
        return mapper.mapToLoginInfoDto(service.getLoginInfo(loginInfoId));
    }

    public List<LoginInfoDto> getUsersLoginInfos(Long userId) throws LoginInfoNotFoundException {
        return mapper.mapToLoginInfoDtoList(service.getAllUserLoginInfos(userId));
    }

    public LoginInfoDto createLoginInfo(@RequestBody LoginInfoDto loginInfoDto) throws UserNotFoundException {
        LoginInfo loginInfo = mapper.mapToLoginInfo(loginInfoDto);
        return mapper.mapToLoginInfoDto(service.saveLoginInfo(loginInfo));

    }

}
