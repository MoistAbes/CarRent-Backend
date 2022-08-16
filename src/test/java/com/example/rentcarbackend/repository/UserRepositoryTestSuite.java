package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void findAllTest(){
        //Given
        User user = new User("test name 1", "test surname 1");
        User user2 = new User("test name 2", "test surname 2");
        User user3 = new User("test name 3", "test surname 3");

        //When
        Long userId = userRepository.save(user).getId();
        Long userId2 = userRepository.save(user2).getId();
        Long userId3 = userRepository.save(user3).getId();

        List<User> userList = userRepository.findAll();

        //Then
        assertEquals(3, userList.size());

        //clean up
        userRepository.deleteById(userId);
        userRepository.deleteById(userId2);
        userRepository.deleteById(userId3);
    }

    @Test
    public void findByIdTest(){
        //Given
        User user = new User("test name 1", "test surname 1");

        //When
        Long userId = userRepository.save(user).getId();

        Optional<User> resultUser = userRepository.findById(userId);

        //Then
        if (resultUser.isPresent()) {
            assertEquals(userId, resultUser.get().getId());
            assertEquals("test name 1", resultUser.get().getName());
            assertEquals("test surname 1", resultUser.get().getSurname());

        }else{
            fail("User not found");
        }

        //clean up
        userRepository.deleteById(userId);

    }

}
