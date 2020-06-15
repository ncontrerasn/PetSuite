package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class FindDogServiceTest {

    @InjectMocks
    FindDogService findDogService;

    @Mock
    DogRepository dogRepository;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @Mock
    WalkPetitionRepository walkPetitionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void find()
    {
        Entero ID = new Entero();

        ID.setEntero(1);

        Dog dog = new Dog();

        dog.setUser("htovars");
        dog.setDog_id(1);
        dog.setDog_age(20);
        dog.setDog_name("Laika");
        dog.setDog_notes("chiquita");
        dog.setDog_height(10f);
        dog.setDog_weight(2f);
        dog.setDog_race("pincher");

        when(dogRepository.findByDogId(any())).thenReturn(dog);

        assertEquals("Laika",findDogService.find(ID).getDog_name());

    }

    @Test
    void myDogList()
    {
        Cadena user = new Cadena();

        user.setCadena("htovars");

        Dog dog = new Dog();

        dog.setUser("htovars");
        dog.setDog_id(1);
        dog.setDog_age(20);
        dog.setDog_name("Laika");
        dog.setDog_notes("chiquita");
        dog.setDog_height(10f);
        dog.setDog_weight(2f);
        dog.setDog_race("pincher");

        List<Dog> dogs = new ArrayList<>();

        dogs.add(dog);

        when(dogRepository.findByUser(any())).thenReturn(dogs);

        assertEquals("Laika",findDogService.myDogList(user).get(0).getDog_name());
    }

    @Test
    void walkerDogList()
    {
        Cadena walker = new Cadena();

        walker.setCadena("ncontreras");

        Dog dog = new Dog();

        dog.setUser("htovars");
        dog.setDog_id(1);
        dog.setDog_age(20);
        dog.setDog_name("Laika");
        dog.setDog_notes("chiquita");
        dog.setDog_height(10f);
        dog.setDog_weight(2f);
        dog.setDog_race("pincher");

        List<Integer> dogs_ids = new ArrayList<>();

        dogs_ids.add(1);

        when(walkInvoiceRepository.findByDog_walker_id(any())).thenReturn(dogs_ids);

        when(dogRepository.findByDogId(anyInt())).thenReturn(dog);

        assertEquals("Laika",findDogService.walkerDogList(walker).get(0).getDog_name());
    }

    @Test
    void findDogsByWalkerAndStatusAccepted()
    {
        Cadena walker = new Cadena();

        walker.setCadena("ncontreras");

        Dog dog = new Dog();

        dog.setUser("htovars");
        dog.setDog_id(1);
        dog.setDog_age(20);
        dog.setDog_name("Laika");
        dog.setDog_notes("chiquita");
        dog.setDog_height(10f);
        dog.setDog_weight(2f);
        dog.setDog_race("pincher");

        List<Integer> accepted = new ArrayList<>();

        accepted.add(1);

        when(walkInvoiceRepository.findByWalkerAndStatusAccepted(anyString(),anyString())).thenReturn(accepted);

        when(dogRepository.findByDogId(anyInt())).thenReturn(dog);

        assertEquals("Laika",findDogService.findDogsByWalkerAndStatusAccepted(walker).get(0).getDog_name());
    }

    @Test
    void finPetitionByDog()
    {
        Cadena dog =  new Cadena();

        dog.setCadena("Laika");

        WalkPetition walkPetition = new WalkPetition();

        walkPetition.setWalk_petition_id(1);
        walkPetition.setUser("ncontreras");
        walkPetition.setWalk_petition_walker_user("htovars");
        walkPetition.setWalk_petition_notes("notax");
        walkPetition.setWalk_petition_duration(5f);
        walkPetition.setWalk_petition_date_time(LocalDateTime.parse("2019-04-28 22:32:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        walkPetition.setDog_id(2);
        walkPetition.setWalk_petition_address("calle la cumbia");

        List<WalkPetition> Petitions = new ArrayList<>();

        Petitions.add(walkPetition);

        when(walkPetitionRepository.findPetitionsByDog(anyString())).thenReturn(Petitions);

        assertEquals(walkPetition.getDog_id(),findDogService.finPetitionByDog(dog).get(0).getDog_id());
    }
}