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
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllDogs() {

        List<Dog> dogs = new ArrayList<>();

        Dog dog = new Dog();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();

        Client client1 = new Client();
        client1.setUser("ncontrerasn");

        Client client2 = new Client();
        client2.setUser("esgonzalezca");

        dog.setUser(client1.getUser());
        dog.setDog_age(4);
        dog.setDog_height((float)23);
        dog.setDog_name("Sol");
        dog.setDog_notes("alérgico a la caca");
        dog.setDog_weight((float)6);

        dog1.setUser(client1.getUser());
        dog1.setDog_age(7);
        dog1.setDog_height((float)34);
        dog1.setDog_name("Luna");
        dog1.setDog_notes("alérgico al polvo");
        dog1.setDog_weight((float)8);

        dog2.setUser(client2.getUser());
        dog2.setDog_age(5);
        dog2.setDog_height((float)45);
        dog2.setDog_name("Papo");
        dog2.setDog_notes("llorón");
        dog2.setDog_weight((float)28);

        dogs.add(dog);
        dogs.add(dog1);
        dogs.add(dog2);

        when(dogRepository.findAll()).thenReturn(dogs);
        assertEquals(3, dogController.getAllDogs().size());

    }

    @Test
    void createDog(){

        Dog dog = new Dog();
        Client client = new Client();
        client.setUser("ncontrerasn");
       
        dog.setUser(client.getUser());
        
        dog.setDog_notes("alérgico al chocolate de café del Himalaya");
        dog.setDog_name("Paquirris");
        dog.setDog_age(3);
        dog.setDog_height((float)67);
        dog.setDog_race("pit bull");
        dog.setDog_weight((float)22);
        
         Dog_Dto dtoDog=new Dog_Dto(dog.getDog_id(), dog.getDog_name(), dog.getDog_race(), dog.getDog_height(), dog.getDog_weight(), dog.getDog_age(), dog.getDog_notes(), dog.getUser());


        when(dogRepository.save(dog)).thenReturn(dog);

        dtoDog = dogController.createDog(dtoDog);
        String dogName = dtoDog.getDog_name();

        assertEquals(dog.getDog_name(), dogName);

    }
}