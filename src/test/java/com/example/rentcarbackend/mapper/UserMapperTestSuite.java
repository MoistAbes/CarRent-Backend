package com.example.rentcarbackend.mapper;

import com.example.rentcarbackend.dto.UserDto;
import com.example.rentcarbackend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTestSuite {

    @Autowired
    UserMapper mapper;

    @Test
    void shouldMapToUser(){

        //Given
        Long id = 1L;
        String name = "name test";
        String surname = "surname test";
        String username = "username test";
        String password = "password test";


        UserDto userDto = new UserDto(
                id,
                name,
                surname,
                username,
                password
        );

        //When
        User mappedUser = mapper.mapToUser(userDto);

        //Then
        assertEquals(id, mappedUser.getId());
        assertEquals(name, mappedUser.getName());
        assertEquals(surname, mappedUser.getSurname());
        assertEquals(username, mappedUser.getUsername());
        assertEquals(password, mappedUser.getPassword());

    }

    @Test
    void shouldMapToUserDto(){

        //Given
        Long id = 1L;
        String name = "name test";
        String surname = "surname test";
        String username = "username test";
        String password = "password test";


        User user = new User(
                id,
                name,
                surname,
                username,
                password
        );

        //When
        UserDto mappedUserDto = mapper.mapToUserDto(user);

        //Then
        assertEquals(id, mappedUserDto.getId());
        assertEquals(name, mappedUserDto.getName());
        assertEquals(surname, mappedUserDto.getSurname());
        assertEquals(username, mappedUserDto.getUsername());
        assertEquals(password, mappedUserDto.getPassword());

    }

    @Test
    void shouldMapToUserDtoList(){
        //Given

        User user1 = new User(
                1L,
                "name test",
                "surname test",
                "username test",
                "password test"
        );
        User user2 = new User(
                2L,
                "name test2",
                "surname test2",
                "username test2",
                "password test2"
        );User user3 = new User(
                3L,
                "name test3",
                "surname test3",
                "username test3",
                "password test3"
        );

        List<User> users = List.of(user1, user2, user3);

        //When
        List<UserDto> userDtos = mapper.mapToUserDtoList(users);


        //Then
        for (int i = 0; i < userDtos.size(); i++){
            assertEquals(users.get(i).getId(), userDtos.get(i).getId());
            assertEquals(users.get(i).getName(), userDtos.get(i).getName());
            assertEquals(users.get(i).getSurname(), userDtos.get(i).getSurname());
            assertEquals(users.get(i).getUsername(), userDtos.get(i).getUsername());
            assertEquals(users.get(i).getPassword(), userDtos.get(i).getPassword());

        }
    }

}