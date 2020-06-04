package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DogDayCareControllerTest {

    @InjectMocks
    DogDayCareController dogDayCareController;

    @Mock
    InfoUserRepository infoUserRepository;

    @Mock
    DogDaycareRepository dogDaycareRepository;

    @BeforeEach
    void setUp() { MockitoAnnotations.initMocks(this); }

    @Test
    void updateAll() {

        DogDayCare_Dto DTO = new DogDayCare_Dto();

        DTO.setDog_daycare_phone("2315467228");
        DTO.setDog_daycare_e_mail("test@unal.edu.co");
        DTO.setDog_daycare_score((float)5.0);
        DTO.setDog_daycare_name("pepito");
        DTO.setDog_daycare_address("zona x");
        DTO.setDog_daycare_type(true);
        DTO.setUser("htovars");
        DTO.setRole("ROLE_DOGDAYCARE");
        DTO.setPassword("1234");

        when(infoUserRepository.findUser(anyString())).thenReturn(DTO.getUser());

        when(infoUserRepository.updateUserPassword(anyString(),anyString())).thenReturn(1);

        when(dogDaycareRepository.updateAddress(anyString(),anyString())).thenReturn(1);

        when(infoUserRepository.updateClientName(anyString(),anyString())).thenReturn(1);

        when(infoUserRepository.updateClientPhone(anyString(),anyString())).thenReturn(1);

        when(infoUserRepository.updateClientEmail(anyString(),anyString())).thenReturn(1);

        when(dogDaycareRepository.updateType(anyBoolean(),anyString())).thenReturn(1);

        assertEquals(DTO, dogDayCareController.updateAll(DTO));

    }
}