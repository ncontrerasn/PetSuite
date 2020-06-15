package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class GetAllDataTest {

    @InjectMocks
    GetAllDataService getAllDataService;

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
        List<DogDaycare> daycares = new ArrayList<>();

        DogDaycare daycare = new DogDaycare();

        Set<DogDaycareService> services =  new HashSet<DogDaycareService>();

        DogDaycareService daycareService1 = new DogDaycareService();
        daycareService1.setDogdaycare_Service_Name("parque de juegos");

        DogDaycareService daycareService2 = new DogDaycareService();
        daycareService2.setDogdaycare_Service_Name("ejercicio");

        services.add(daycareService1);

        daycare.setPhone("2315467228");
        daycare.setE_mail("test@unal.edu.co");
        daycare.setName("pepito");
        daycare.setDog_daycare_address("zona x");
        daycare.setDog_daycare_type(true);
        daycare.setUser("htovars");
        daycare.setRole("ROLE_DOGDAYCARE");
        daycare.setPassword("1234");
        daycare.setDogDaycareServices(services);
        daycare.setDog_daycare_score(3.5f);

        daycares.add(daycare);

        daycare = new DogDaycare();

        services.add(daycareService2);

        daycare.setPhone("23414215125");
        daycare.setE_mail("test2@unal.edu.co");
        daycare.setName("pepito loco");
        daycare.setDog_daycare_address("zona y");
        daycare.setDog_daycare_type(true);
        daycare.setUser("ncontrerasn");
        daycare.setRole("ROLE_DOGDAYCARE");
        daycare.setPassword("1234");
        daycare.setDogDaycareServices(services);
        daycare.setDog_daycare_score(2.0f);

        daycares.add(daycare);

        when(dogDaycareRepository.findAll()).thenReturn(daycares);

        assertEquals("pepito",getAllDataService.getAllDogDayCares().get(0).getDog_daycare_name());

    }

    @Test
    void getAllPetitions()
    {
        List<WalkPetition> walkPetitions = new ArrayList<>();

        WalkPetition walkPetition = new WalkPetition();

        walkPetition.setWalk_petition_id(1);
        walkPetition.setUser("ncontreras");
        walkPetition.setWalk_petition_walker_user("htovars");
        walkPetition.setWalk_petition_notes("notax");
        walkPetition.setWalk_petition_duration(5f);
        walkPetition.setWalk_petition_date_time(LocalDateTime.parse("2019-04-28 22:32:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        walkPetition.setDog_id(2);
        walkPetition.setWalk_petition_address("calle la cumbia");

        walkPetitions.add(walkPetition);

        walkPetition = new WalkPetition();

        walkPetition.setWalk_petition_id(2);
        walkPetition.setUser("Diego");
        walkPetition.setWalk_petition_walker_user("htovars");
        walkPetition.setWalk_petition_notes("notax");
        walkPetition.setWalk_petition_duration(3f);
        walkPetition.setWalk_petition_date_time(LocalDateTime.parse("2019-04-28 22:32:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        walkPetition.setDog_id(1);
        walkPetition.setWalk_petition_address("calle la macarena");

        walkPetitions.add(walkPetition);

        Dog dog1 = new Dog();

        dog1.setUser("ncontreras");
        dog1.setDog_id(2);
        dog1.setDog_age(20);
        dog1.setDog_name("Laika");
        dog1.setDog_notes("chiquita");
        dog1.setDog_height(10f);
        dog1.setDog_weight(2f);
        dog1.setDog_race("pincher");

        Dog dog2 = new Dog();

        dog2.setUser("Diego");
        dog2.setDog_id(1);
        dog2.setDog_age(20);
        dog2.setDog_name("Carlos");
        dog2.setDog_notes("Loco");
        dog2.setDog_height(10f);
        dog2.setDog_weight(2f);
        dog2.setDog_race("Bulldog");

        when(walkPetitionRepository.findAll()).thenReturn(walkPetitions);

        when(dogRepository.findById(anyInt())).thenReturn(java.util.Optional.of(dog1),java.util.Optional.of(dog2));

        assertEquals("ncontreras",getAllDataService.getAllPetitions().get(0).getUser());

    }
}