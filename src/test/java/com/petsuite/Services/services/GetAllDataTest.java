package com.petsuite.Services.services;

import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class GetAllDataTest {

    @InjectMocks
    GetAllData getAllData;

    @Mock
    DogDaycareRepository dogDaycareRepository;

    @Mock
    DogRepository dogRepository;

    @Mock
    WalkPetitionRepository walkPetitionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllDogDayCares()
    {

    }

    @Test
    void getAllPetitions()
    {
        
    }
}