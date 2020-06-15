package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Flotante;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.repository.DogDaycareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SearchDogDayCareServiceTest {

    @InjectMocks
    SearchDogDayCareService searchDogDayCare;

    @Mock
    DogDaycareRepository dogDaycareRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    List<DogDaycare> dayCaresList()
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

        return daycares;
    }

    @Test
    void searchDayCareByNameAndService()
    {
        Cadena name = new Cadena();
        name.setCadena("p");

        List<DogDaycare> daycares;

        List<String> users = new ArrayList<>();

        daycares = dayCaresList();

        users.add(daycares.get(0).getUser());

        users.add(daycares.get(1).getUser());

        when(dogDaycareRepository.searchByName(anyString())).thenReturn(users);
        when(dogDaycareRepository.searchByService(anyString())).thenReturn(users);

        Optional<DogDaycare> DC = Optional.of(daycares.get(0));

        Optional<DogDaycare> DC2 = Optional.of(daycares.get(1));

        when(dogDaycareRepository.findById(anyString())).thenReturn(DC,DC2);

        assertEquals(2, searchDogDayCare.searchDayCareByNameAndService(name).size());
    }

    @Test
    void searchDayCareByName()
    {
        Cadena name = new Cadena();
        name.setCadena("pepito");

        List<DogDaycare> daycares;

        List<String> users = new ArrayList<>();

        daycares = dayCaresList();

        users.add(daycares.get(0).getUser());

        users.add(daycares.get(1).getUser());

        when(dogDaycareRepository.searchByName(anyString())).thenReturn(users);

        Optional<DogDaycare> DC = Optional.of(daycares.get(0));

        Optional<DogDaycare> DC2 = Optional.of(daycares.get(1));

        when(dogDaycareRepository.findById(anyString())).thenReturn(DC,DC2);

        assertEquals(2, searchDogDayCare.searchDayCareByName(name).size());
    }

    @Test
    void searchDayCareByService()
    {
        Cadena name = new Cadena();
        name.setCadena("ejercicio");

        List<DogDaycare> daycares;

        List<String> users = new ArrayList<>();

        daycares = dayCaresList();

        users.add(daycares.get(1).getUser());

        when(dogDaycareRepository.searchByService(anyString())).thenReturn(users);

        Optional<DogDaycare> DC2 = Optional.of(daycares.get(1));

        when(dogDaycareRepository.findById(anyString())).thenReturn(DC2);

        assertEquals(1, searchDogDayCare.searchDayCareByService(name).size());
    }

    @Test
    void searchDayCareByScore()
    {
        Flotante score = new Flotante();
        score.setFlotante(3.0f);

        List<DogDaycare> daycares;

        List<String> users = new ArrayList<>();

        daycares = dayCaresList();

        users.add(daycares.get(0).getUser());

        when(dogDaycareRepository.searchByScore(anyFloat(),anyFloat())).thenReturn(users);

        Optional<DogDaycare> DC2 = Optional.of(daycares.get(0));

        when(dogDaycareRepository.findById(anyString())).thenReturn(DC2);

        assertEquals(1, searchDogDayCare.searchDayCareByScore(score).size());
    }
}