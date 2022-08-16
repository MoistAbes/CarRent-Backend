package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.dto.LoginInfoDto;
import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginInfoMapper {

    private final UserDbService userDbService;

    public LoginInfoDto mapToLoginInfoDto(LoginInfo loginInfo){
        return new LoginInfoDto(
                loginInfo.getId(),
                loginInfo.getUser().getId(),
                loginInfo.getLogInTime()
        );
    }

    public LoginInfo mapToLoginInfo(LoginInfoDto loginInfoDto) throws UserNotFoundException {
        return new LoginInfo(
                loginInfoDto.getId(),
                userDbService.getUser(loginInfoDto.getUserId()),
                loginInfoDto.getLogInTime()
        );
    }

    public List<LoginInfoDto> mapToLoginInfoDtoList(List<LoginInfo> loginInfos){
        return loginInfos.stream()
                .map(this::mapToLoginInfoDto)
                .collect(Collectors.toList());
    }

}
