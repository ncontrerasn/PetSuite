package com.petsuite.Services.services;

import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GetServicesServiceTest {

    @InjectMocks
    GetServicesService getServicesService;

    @Mock
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getMyServices()
    {
        String user = "htovars";

        List<DogDaycareService> services =  new ArrayList<DogDaycareService>();

        DogDaycareService daycareService1 = new DogDaycareService();
        daycareService1.setDogdaycare_Service_Name("parque de juegos");

        DogDaycareService daycareService2 = new DogDaycareService();
        daycareService2.setDogdaycare_Service_Name("ejercicio");

        services.add(daycareService1);
        services.add(daycareService2);

        when(dogDaycareServiceRepository.findMyServicesByUser(anyString())).thenReturn(services);

        assertEquals(2,getServicesService.getMyServices(user).size());

    }
}