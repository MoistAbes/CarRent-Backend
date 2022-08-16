package com.example.rentcarbackend.repository;

import com.example.rentcarbackend.entity.LoginInfo;
import com.example.rentcarbackend.entity.User;
import org.hibernate.type.LocalDateType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginInfoRepositoryTest {

    @Autowired
    LoginInfoRepository repository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findAllTest(){

        //Given
        User user = new User("test name", "test surname", "test username", "test password");

        LoginInfo loginInfo = new LoginInfo(user, LocalDateTime.now());
        LoginInfo loginInfo2 = new LoginInfo(user, LocalDateTime.now());
        LoginInfo loginInfo3 = new LoginInfo(user, LocalDateTime.now());

        List<LoginInfo> loginInfos = List.of(loginInfo, loginInfo2, loginInfo3);
        user.setLoginInfos(loginInfos);

        //When
        Long userId = userRepository.save(user).getId();

        Long id = repository.save(loginInfo).getId();
        Long id2 = repository.save(loginInfo2).getId();
        Long id3 = repository.save(loginInfo3).getId();

        List<LoginInfo> resultLoginInfoList = repository.findAll();

        //Then
        assertEquals(3, resultLoginInfoList.size());

        //Clean up
        repository.deleteById(id);
        repository.deleteById(id2);
        repository.deleteById(id3);
        userRepository.deleteById(userId);

    }

    @Test
    void findByIdTest(){

        //Given
        User user = new User("test name", "test surname", "test username", "test password");

        LocalDateTime localDateTime = LocalDateTime.now();

        LoginInfo loginInfo = new LoginInfo(user, localDateTime);


        List<LoginInfo> loginInfos = List.of(loginInfo);
        user.setLoginInfos(loginInfos);

        //When
        Long userId = userRepository.save(user).getId();

        LoginInfo savedLoginInfo = repository.save(loginInfo);


        //Then
        assertEquals("test name", savedLoginInfo.getUser().getName());

        //Clean up
        repository.deleteById(savedLoginInfo.getId());
        userRepository.deleteById(userId);

    }


}
