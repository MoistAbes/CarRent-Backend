package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.RentFacade;
import com.example.rentcarbackend.controller.facede.RentedCarFacade;
import com.example.rentcarbackend.domain.CarRentStatus;
import com.example.rentcarbackend.dto.RentDto;
import com.example.rentcarbackend.dto.RentedCarDto;
import com.example.rentcarbackend.entity.Rent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(RentController.class)
class RentControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RentFacade rentFacade;

    @Test
    void shouldGetAllRents() throws Exception{
        //Given

        when(rentFacade.getRents()).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetRent() throws Exception{
        //Given
        Long id = 10L;
        RentDto rentDto = new RentDto(
                10L,
                10L,
                10L,
                LocalDate.of(1009, 5, 12),
                LocalDate.of(1009, 5, 18)
        );
        when(rentFacade.getRent(id)).thenReturn(rentDto);

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rents/rent/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(10)));
    }

    @Test
    void shouldGetAllUserRents() throws Exception{
        //Given
        Long id = 10L;
        when(rentFacade.getAllUserRents(id)).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rents/userRents/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }


}