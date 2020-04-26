package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DogControllerTest {

    @InjectMocks
    DogController dogController;

    @Mock
    DogRepository dogRepository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllDogs() {

        List<Dog> dogs = new ArrayList<>();
        Dog dog = new Dog();
        Dog dog1 = new Dog();
        Client client = new Client();
        client.setUser("ncontrerasn");

        dog.setClient_id(client.getUser());
        dog.setDog_age(4);
        dog.setDog_height((float)23);
        dog.setDog_id(1);
        dog.setDog_name("Sol");
        dog.setDog_notes("alérgico a la caca");
        dog.setDog_weight((float)6);

        dog1.setClient_id(client.getUser());
        dog1.setDog_age(7);
        dog1.setDog_height((float)34);
        dog1.setDog_id(1);
        dog1.setDog_name("Luna");
        dog1.setDog_notes("alérgico al polvo");
        dog1.setDog_weight((float)8);

        dogs.add(dog);
        dogs.add(dog1);

        when(dogRepository.findAll()).thenReturn(dogs);
        assertEquals(2, dogController.getAllDogs().size());

    }

    @Test
    void createDog(){
        Dog_Dto dog_dto = new Dog_Dto();
        Dog dog = new Dog();
        Client client = new Client();
        client.setUser("ncontrerasn");

        dog_dto.setClient_id(client.getUser());
        dog_dto.setDog_notes("alérgico al chocolate de café del Himalaya");
        dog_dto.setDog_name("Paquirris");
        dog_dto.setDog_age(3);
        dog_dto.setDog_height((float)67);
        dog_dto.setDog_race("pit bull");
        dog_dto.setDog_weight((float)22);

        dog.setClient_id(dog_dto.getClient_id());
        dog.setDog_notes(dog_dto.getDog_notes());
        dog.setDog_name(dog_dto.getDog_name());
        dog.setDog_age(dog_dto.getDog_age());
        dog.setDog_height(dog_dto.getDog_height());
        dog.setDog_race(dog_dto.getDog_race());
        dog.setDog_weight(dog_dto.getDog_weight());

        when(dogRepository.save(dog)).thenReturn(dog);

        dog_dto = dogController.createDog(dog_dto);
        String dogName = dog_dto.getDog_name();

        assertEquals(dog_dto.getDog_name(), dogName);

    }
}