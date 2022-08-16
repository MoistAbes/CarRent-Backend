package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.dto.UserDto;
import com.example.rentcarbackend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getUsername(),
                userDto.getPassword()
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getPassword()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){

        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
