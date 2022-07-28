package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.domain.UserDto;
import com.example.rentcarbackend.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname()
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userList){
            userDtoList.add(new UserDto(user.getId(), user.getName(), user.getSurname()));
        }

        return userDtoList;
    }
}
