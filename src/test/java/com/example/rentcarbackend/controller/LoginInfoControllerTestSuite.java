package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.LoginInfoFacade;
import com.example.rentcarbackend.controller.facede.RentFacade;
import com.example.rentcarbackend.dto.LoginInfoDto;
import com.example.rentcarbackend.dto.RentDto;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(LoginInfoController.class)
class LoginInfoControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LoginInfoFacade loginInfoFacade;

    @Test
    void shouldGetAllInfos() throws Exception{
        //Given

        when(loginInfoFacade.getLoginInfos()).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/loginInfo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetLoginInfo() throws Exception{
        //Given
        Long id = 10L;
        LoginInfoDto loginInfoDto = new LoginInfoDto(
                10L,
                2L,
                LocalDateTime.now()
        );
        when(loginInfoFacade.getLoginInfo(id)).thenReturn(loginInfoDto);

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/loginInfo/logininfo/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(2)));
    }

    @Test
    void shouldGetAllUserLoginInfo() throws Exception{
        //Given
        Long id = 10L;

        when(loginInfoFacade.getUsersLoginInfos(id)).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/loginInfo/user/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
}