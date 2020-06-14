package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCare_Service_Dto;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateServiceTest {

    @InjectMocks
    CreateService createService;

    @Mock
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createService()
    {
        DogDayCare_Service_Dto dogDayCare_service_dto = new DogDayCare_Service_Dto();

        dogDayCare_service_dto.setDogdaycare_Service_ClientId("ncontreras");
        dogDayCare_service_dto.setDogdaycare_Service_Description("servicio de test");
        dogDayCare_service_dto.setDogdaycare_Service_Name("test");
        dogDayCare_service_dto.setDogdaycare_Service_Price(1000f);

        DogDaycareService dogDaycareService = new DogDaycareService();

        dogDaycareService.setDog_daycare_service_id(1);
        dogDaycareService.setDogdaycare_Service_Name("test");

        when(dogDaycareServiceRepository.save(any())).thenReturn(null);

        assertEquals("ncontreras",createService.createService(dogDayCare_service_dto).getDogdaycare_Service_ClientId());

    }
}