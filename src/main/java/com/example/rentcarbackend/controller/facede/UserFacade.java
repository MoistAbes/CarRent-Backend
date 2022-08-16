package com.example.rentcarbackend.controller.facede;

import com.example.rentcarbackend.dto.UserDto;
import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.entity.User;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.exception.UsernameIsTakenException;
import com.example.rentcarbackend.mapper.UserMapper;
import com.example.rentcarbackend.service.LoginInfoDbService;
import com.example.rentcarbackend.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserDbService userDbService;
    private final UserMapper userMapper;
    private final LoginInfoDbService loginInfoDbService;


    public List<UserDto> getAllUsers(){
        List<User> users = userDbService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }
    public UserDto getUser(Long userId) throws UserNotFoundException {
        User user = userDbService.getUser(userId);
        return userMapper.mapToUserDto(user);
    }

    public boolean createUser(UserDto userDto) throws UsernameIsTakenException {
        if  (checkIfUsernameTaken(userDto.getUsername())) {
            User user = userMapper.mapToUser(userDto);
            userDbService.saveUser(user);
            return true;
        }else {
            throw new UsernameIsTakenException();
        }
    }

    public UserDto getAuthentication(String username, String password){
        User authenticatedUser = userDbService.getAuthentication(username, password);
        if (authenticatedUser.getId() != null){
            loginInfoDbService.saveLoginInfo(new LoginInfo(authenticatedUser));
        }
        return userMapper.mapToUserDto(authenticatedUser);
    }

    public UserDto updateUser(UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        return userMapper.mapToUserDto(userDbService.saveUser(user));

    }

    private boolean checkIfUsernameTaken(String username){
        long count = userDbService.getAllUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .count();

        return count <= 0;
    }

}
