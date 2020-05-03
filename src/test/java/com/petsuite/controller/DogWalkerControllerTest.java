package com.petsuite.controller;

import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.basics.Cadena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DogWalkerControllerTest {

    @InjectMocks
    DogWalkerController dogWalkerController;

    @Mock
    DogRepository dogRepository;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDogList() {
        String dogWalkerUser = "ncontrerasn";
        List<Dog> dogs = new ArrayList<>();
        List<Integer> dogsIds= new ArrayList<>();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Client client1 = new Client();
        Client client2 = new Client();

        dogsIds.add(1);
        dogsIds.add(2);

        client1.setUser("ncontrerasn");
        client2.setUser("esgonzalezca");

        dog1.setUser(client1.getUser());
        //dog1.setClient_d(client1);
        dog1.setDog_age(7);
        dog1.setDog_height((float)34);
        dog1.setDog_name("Luna");
        dog1.setDog_notes("alérgico al polvo");
        dog1.setDog_weight((float)8);

        dog2.setUser(client2.getUser());
        //dog2.setClient_d(client2);
        dog2.setDog_age(5);
        dog2.setDog_height((float)45);
        dog2.setDog_name("Papo");
        dog2.setDog_notes("llorón");
        dog2.setDog_weight((float)28);

        dogs.add(dog1);
        dogs.add(dog2);

        when(walkInvoiceRepository.findByDog_walker_id(anyString())).thenReturn(dogsIds);
        for(int i = 0; i < dogsIds.size(); i++)
            when(dogRepository.findById(anyInt())).thenReturn(java.util.Optional.of(dogs.get(i)));
        //Cadena nombre= new Cadena()
        assertEquals(1, dogWalkerController.dogList(new Cadena("dogWalkerUser")).size());
    }

    @Test
    void testPendingDogList() {
        Cadena dogWalkerUser = new Cadena("ncontrerasn");
        List<Dog> dogs = new ArrayList<>();
        List<Integer> dogsIds= new ArrayList<>();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Client client1 = new Client();
        Client client2 = new Client();

        dogsIds.add(1);
        dogsIds.add(2);

        client1.setUser("ncontrerasn");
        client2.setUser("esgonzalezca");

        dog1.setUser(client1.getUser());
        //dog1.setClient_d(client1);
        dog1.setDog_age(7);
        dog1.setDog_height((float)34);
        dog1.setDog_name("Luna");
        dog1.setDog_notes("alérgico al polvo");
        dog1.setDog_weight((float)8);

        dog2.setUser(client2.getUser());
        //dog2.setClient_d(client2);
        dog2.setDog_age(5);
        dog2.setDog_height((float)45);
        dog2.setDog_name("Papo");
        dog2.setDog_notes("llorón");
        dog2.setDog_weight((float)28);

        dogs.add(dog1);
        dogs.add(dog2);

        when(walkInvoiceRepository.findByDog_walker_id_and_status_true(anyString())).thenReturn(dogsIds);
        for(int i = 0; i < dogsIds.size(); i++)
            when(dogRepository.findById(anyInt())).thenReturn(java.util.Optional.of(dogs.get(i)));
        assertEquals(1, dogWalkerController.PendingDogList((dogWalkerUser)).size());
    }

    @Test
    void CompletedDogList() {
        String dogWalkerUser = "ncontrerasn";
        List<Dog> dogs = new ArrayList<>();
        List<Integer> dogsIds= new ArrayList<>();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Client client1 = new Client();
        Client client2 = new Client();

        dogsIds.add(1);
        dogsIds.add(2);

        client1.setUser("ncontrerasn");
        client2.setUser("esgonzalezca");

        dog1.setUser(client1.getUser());
       // dog1.setClient_d(client1);
        dog1.setDog_age(7);
        dog1.setDog_height((float)34);
        dog1.setDog_name("Luna");
        dog1.setDog_notes("alérgico al polvo");
        dog1.setDog_weight((float)8);

        dog2.setUser(client2.getUser());
        //dog2.setClient_d(client2);
        dog2.setDog_age(5);
        dog2.setDog_height((float)45);
        dog2.setDog_name("Papo");
        dog2.setDog_notes("llorón");
        dog2.setDog_weight((float)28);

        dogs.add(dog1);
        dogs.add(dog2);

        when(walkInvoiceRepository.findByDog_walker_id_and_status_false(anyString())).thenReturn(dogsIds);
        for(int i = 0; i < dogsIds.size(); i++)
            when(dogRepository.findById(anyInt())).thenReturn(java.util.Optional.of(dogs.get(i)));
        assertEquals(1, dogWalkerController.CompletedDogList(dogWalkerUser).size());
    }

}