package com.example.rentcarbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
}
