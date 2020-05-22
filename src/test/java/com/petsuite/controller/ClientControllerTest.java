package com.petsuite.controller;

import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.basics.Cadena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ClientControllerTest {

    @InjectMocks
    ClientController clientController;

    @Mock
    DogRepository dogRepository;

    @Mock
    DogDaycareRepository dogDaycareRepository;

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

    @Test
    void testSearchDayCare(){

        Cadena name = new Cadena();
        name.setCadena("pepito");

        List<DogDaycare> daycares = new ArrayList<>();

        List<String> users = new ArrayList<>();

        DogDaycare daycare = new DogDaycare();

        daycare.setPhone("2315467228");
        daycare.setE_mail("test@unal.edu.co");
        daycare.setDog_daycare_score((float)5.0);
        daycare.setName("pepito");
        daycare.setDog_daycare_address("zona x");
        daycare.setDog_daycare_type(true);
        daycare.setUser("htovars");
        daycare.setRole("ROLE_DOGDAYCARE");
        daycare.setPassword("1234");

        daycares.add(daycare);
        users.add(daycare.getUser());

        daycare = new DogDaycare();

        daycare.setPhone("23414215125");
        daycare.setE_mail("test2@unal.edu.co");
        daycare.setDog_daycare_score((float)4.0);
        daycare.setName("pepito loco");
        daycare.setDog_daycare_address("zona y");
        daycare.setDog_daycare_type(true);
        daycare.setUser("ncontrerasn");
        daycare.setRole("ROLE_DOGDAYCARE");
        daycare.setPassword("1234");

        daycares.add(daycare);
        users.add(daycare.getUser());

        when(dogDaycareRepository.searchByName(anyString())).thenReturn(users);

        Optional<DogDaycare> DC = Optional.of(daycares.get(0));

        Optional<DogDaycare> DC2 = Optional.of(daycares.get(1));

        when(dogDaycareRepository.findById(anyString())).thenReturn(DC,DC2);

        assertEquals(2, clientController.searchDayCareByNameAndService(name).size());

    }
}