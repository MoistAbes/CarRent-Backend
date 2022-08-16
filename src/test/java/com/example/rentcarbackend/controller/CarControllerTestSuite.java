package com.example.rentcarbackend.controller;

import com.example.rentcarbackend.controller.facede.CarFacade;
import com.example.rentcarbackend.controller.facede.RentFacade;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
class CarControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CarFacade carFacade;

    @Test
    void shouldGetAllCars() throws Exception{
        //Given

        when(carFacade.getCars()).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetAllBrands() throws Exception{
        //Given

        when(carFacade.getBrands()).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/brands")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetAllTypes() throws Exception{
        //Given

        when(carFacade.getTypes()).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetAllYears() throws Exception{
        //Given

        when(carFacade.getYears()).thenReturn(List.of());

        //When & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cars/years")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

}