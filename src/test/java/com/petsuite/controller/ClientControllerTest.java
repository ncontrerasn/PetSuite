package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.services.SearchDogDayCareService;
import com.petsuite.Services.services.interfaces.ISearchDogDayCare;
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

    @Mock
    SearchDogDayCareService searchDogDayCare;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    List<DogDaycare> dayCaresList()
    {
        List<DogDaycare> daycares = new ArrayList<>();

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

        return daycares;
    }

    List<DogDayCare_Dto> dayCaresDTOList()
    {
        List<DogDayCare_Dto> daycares = new ArrayList<>();

        DogDayCare_Dto daycare = new DogDayCare_Dto();

        daycare.setDog_daycare_phone("2315467228");
        daycare.setDog_daycare_e_mail("test@unal.edu.co");
        daycare.setDog_daycare_score((float)5.0);
        daycare.setDog_daycare_name("pepito");
        daycare.setDog_daycare_address("zona x");
        daycare.setDog_daycare_type(true);
        daycare.setUser("htovars");
        daycare.setRole("ROLE_DOGDAYCARE");
        daycare.setPassword("1234");

        daycares.add(daycare);

        daycare = new DogDayCare_Dto();

        daycare.setDog_daycare_phone("23414215125");
        daycare.setDog_daycare_e_mail("test2@unal.edu.co");
        daycare.setDog_daycare_score((float)4.0);
        daycare.setDog_daycare_name("pepito loco");
        daycare.setDog_daycare_address("zona y");
        daycare.setDog_daycare_type(true);
        daycare.setUser("ncontrerasn");
        daycare.setRole("ROLE_DOGDAYCARE");
        daycare.setPassword("1234");

        daycares.add(daycare);

        return daycares;
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

        DogDaycareService daycareService= new DogDaycareService();

        daycareService.setDogdaycare_Service_Name("parque de juegos");

        List<DogDayCare_Dto> daycares = new ArrayList<>();

        List<String> users = new ArrayList<>();

        daycares = dayCaresDTOList();

        users.add(daycares.get(0).getUser());

        users.add(daycares.get(1).getUser());

        when(searchDogDayCare.searchDayCareByName(name)).thenReturn(daycares);

        assertEquals(2, clientController.searchDayCareByName(name).size());

    }
}