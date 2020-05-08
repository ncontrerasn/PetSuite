package com.petsuite.controller;

import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.basics.Cadena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ClientControllerTest {

    @InjectMocks
    ClientController clientController;

    @Mock
    DogRepository dogRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMyDogList() {

        List<Dog> dogs = new ArrayList<>();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Client client = new Client();
        client.setUser("ncontrerasn");

        dog1.setUser(client.getUser());
        dog1.setDog_age(4);
        dog1.setDog_height((float)23);
        dog1.setDog_name("Sol");
        dog1.setDog_notes("alérgico a la caca");
        dog1.setDog_weight((float)6);

        dog2.setUser(client.getUser());
        dog2.setDog_age(7);
        dog2.setDog_height((float)34);
        dog2.setDog_name("Luna");
        dog2.setDog_notes("alérgico al polvo");
        dog2.setDog_weight((float)8);

        dogs.add(dog1);
        dogs.add(dog2);

        Cadena cad = new Cadena();

        cad.setCadena("ncontrerasn");

        when(dogRepository.findByUser(anyString())).thenReturn(dogs);
        assertEquals(2, clientController.myDogList(cad).size());
    }
}