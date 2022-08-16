package com.example.rentcarbackend.service;

import com.example.rentcarbackend.entity.User;
import com.example.rentcarbackend.exception.UserNotFoundException;
import com.example.rentcarbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {


    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(final Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(final User user) {
        return repository.save(user);
    }

    public User getAuthentication(String username, String password){
        List<User> userList = repository.findAll();
        for (User user : userList){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }

        return new User();
    }

    public void deleteUser(Long userId){
        repository.deleteById(userId);
    }


}
