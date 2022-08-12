package com.example.rentcarbackend.service;

import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.exception.LoginInfoNotFoundException;
import com.example.rentcarbackend.repository.LoginInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginInfoDbService {

    private final LoginInfoRepository repository;

    public List<LoginInfo> getAllLoginInfos() {
        return repository.findAll();
    }
    public List<LoginInfo> getAllUserLoginInfos(Long userId) {
        return repository.findAllByUserId(userId);
    }


    public LoginInfo getLoginInfo(final Long userLoginInfo) throws LoginInfoNotFoundException {
        return repository.findById(userLoginInfo).orElseThrow(LoginInfoNotFoundException::new);
    }



    public LoginInfo saveLoginInfo(final LoginInfo loginInfo) {
        return repository.save(loginInfo);
    }

}
