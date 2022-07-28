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

}
