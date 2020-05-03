package com.petsuite.controller;

import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.basics.Cadena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class WalkInvoiceControllerTest {

    @InjectMocks
    WalkInvoiceController walkInvoiceController;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @Mock
    WalkPetitionRepository walkPetitionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllInvoices() {

        List<WalkInvoice> Invoices = new ArrayList<>();

        WalkInvoice In = new WalkInvoice();

        In.setClient_id("htovars");
        In.setDog_id(1);
        In.setDog_walker_id("Diego");
        In.setWalk_invoice_address("centro");
        In.setWalk_invoice_date((LocalDateTime.parse("2019-04-28T22:32:38.536")));
        In.setWalk_invoice_duration((float)15.5);
        In.setWalk_invoice_id(1);
        In.setWalk_invoice_price((float)15);
        In.setWalker_score((float)3.0);
        In.setWalk_invoice_status("true");

        Invoices.add(In);

        In = new WalkInvoice();

        In.setClient_id("ncontrerasn");
        In.setDog_id(2);
        In.setDog_walker_id("esgonzalezca");
        In.setWalk_invoice_address("lejos");
        In.setWalk_invoice_date((LocalDateTime.parse("2019-04-28T22:32:38.536")));
        In.setWalk_invoice_duration((float)25.5);
        In.setWalk_invoice_id(2);
        In.setWalk_invoice_price((float)35);
        In.setWalker_score((float)4.0);
        In.setWalk_invoice_status("false");

        Invoices.add(In);

        when(walkInvoiceRepository.findAll()).thenReturn(Invoices);
        assertEquals(2,walkInvoiceController.getAllInvoices().size());

    }

    @Test
    void createInvoice() {

        Dog dog1 = new Dog();

        dog1.setDog_id(1);
        dog1.setUser("htovars");
        dog1.setDog_age(7);
        dog1.setDog_height((float)34);
        dog1.setDog_name("Luna");
        dog1.setDog_notes("alérgico al polvo");
        dog1.setDog_weight((float)8);

        WalkInvoice_Dto In_Dto = new WalkInvoice_Dto();

        In_Dto.setDog_walker_id("esgonzalezca");
        In_Dto.setWalk_invoice_price((float)35);
        In_Dto.setWalker_score((float)4.0);
        In_Dto.setWalk_invoice_status("false");


        List<WalkInvoice> Invoices = new ArrayList<>();

        List<WalkPetition> Petitions = new ArrayList<>();

        WalkPetition Pet = new WalkPetition();

        Pet.setUser("htovars");
        Pet.setDog_id(1);
        Pet.setWalk_petition_address("centro");
        Pet.setWalk_petition_date_time((LocalDateTime.parse("2019-04-28T22:32:38.536")));
        Pet.setWalk_petition_duration((float)15.5);
        Pet.setWalk_petition_id(1);
        Pet.setWalk_petition_notes("x");

        Petitions.add(Pet);

        when(walkPetitionRepository.findPetitionsByDogAndByUser(anyString(),anyString())).thenReturn(Pet);

        WalkInvoice In =  new WalkInvoice();

        In.setClient_id(Petitions.get(0).getUser());
        In.setDog_id(Petitions.get(0).getDog_id());
        In.setDog_walker_id(In_Dto.getDog_walker_id());
        In.setWalk_invoice_address(Petitions.get(0).getWalk_petition_address());
        In.setWalk_invoice_date(Petitions.get(0).getWalk_petition_date_time());
        In.setWalk_invoice_duration(Petitions.get(0).getWalk_petition_duration());
        In.setWalk_invoice_id(1);
        In.setWalk_invoice_price(In_Dto.getWalk_invoice_price());
        In.setWalker_score(In_Dto.getWalker_score());
        In.setWalk_invoice_status(In_Dto.getWalk_invoice_status());
        In.setWalk_invoice_notes(Petitions.get(0).getWalk_petition_notes());


        when(walkInvoiceRepository.save(any())).thenReturn(Invoices.add(In));

        assertEquals(0,Petitions.size());
        assertEquals((Integer)1,walkInvoiceController.createInvoice(In_Dto).getWalk_invoice_id());

    }

    @Test
    void scoreDogWalker() {
    }
}