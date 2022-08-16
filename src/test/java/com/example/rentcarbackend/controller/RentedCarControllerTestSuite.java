package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.RentedCarFacade;
import com.example.rentcarbackend.controller.facede.UserFacade;
import com.example.rentcarbackend.domain.CarRentStatus;
import com.example.rentcarbackend.dto.RentedCarDto;
import com.example.rentcarbackend.dto.UserDto;
import com.example.rentcarbackend.exception.CarNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(RentedCarController.class)
class RentedCarControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RentedCarFacade rentedCarFacade;

    @Test
    void shouldGetRentedCar() throws Exception {
        //Given
        Long id = 10L;
        RentedCarDto rentedCarDto = new RentedCarDto(
                10,
                1998,
                "test brand",
                "test model",
                "test tpe",
                CarRentStatus.RENTED
        );
        when(rentedCarFacade.getRentedCar(id)).thenReturn(rentedCarDto);

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentedCars/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("test brand")));
    }

    @Test
    void shouldGetAllRentedCars() throws Exception {
        //Given

        when(rentedCarFacade.getAllCars()).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentedCars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldCreateRentedCar() throws Exception{
        //Given
        RentedCarDto rentedCarDto = new RentedCarDto(
                10,
                1998,
                "test brand",
                "test model",
                "test tpe",
                CarRentStatus.RENTED
        );
        when(rentedCarFacade.createRentedCar(rentedCarDto)).thenReturn(10L);



        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentedCars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }




}