package com.example.rentcarbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoginInfoDto {

    private Long id;
    private Long userId;
    private LocalDateTime LogInTime;

}
