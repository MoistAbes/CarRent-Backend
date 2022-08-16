package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.UserFacade;
import com.example.rentcarbackend.dto.UserDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTestSuite {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserFacade userFacade;

    @Test
    void shouldGetUser() throws Exception{
        //Given
        Long userId = 1L;
        UserDto userDto = new UserDto(
                "name test",
                "surname test",
                "username test",
                "password test"
        );
        when(userFacade.getUser(userId)).thenReturn(userDto);

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name test")));
    }

    @Test
    void shouldFetchUsers() throws Exception{
        //Given
        when(userFacade.getAllUsers()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldCreateUser() throws Exception{
        //Given
        UserDto userDto = new UserDto(
                1L,
                "name test",
                "surname test",
                "username test",
                "password test"
        );
        when(userFacade.createUser(userDto)).thenReturn(true);

        Long id;


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

        //Clean up


    }

    @Test
    void shouldAuthenticateUser() throws Exception{
        //Given
        String username = "test username";
        String password = "test password";
        UserDto userDto = new UserDto(
                1L,
                "name test",
                "surname test",
                "test username",
                "test passwrod"
        );

        when(userFacade.getAuthentication(username, password)).thenReturn(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users/" + username + "/" + password)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name test")));

    }

}