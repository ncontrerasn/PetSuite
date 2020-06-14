package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.*;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CreateInvoiceServiceTest {

    @InjectMocks
    CreateInvoiceService createInvoiceService;

    @Mock
    ProposePrice proposePrice;

    @Mock
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Mock
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Mock
    Service_InvoiceRepository service_invoiceRepository;

    @Mock
    WalkPetitionRepository walkPetitionRepository;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createDogDayCareInvoice()
    {
        DogDaycareService daycareService1 = new DogDaycareService();
        daycareService1.setDogdaycare_Service_Name("parque de juegos");
        daycareService1.setDog_daycare_service_id(1);

        DogDaycareService daycareService2 = new DogDaycareService();
        daycareService2.setDogdaycare_Service_Name("ejercicio");
        daycareService2.setDog_daycare_service_id(2);

        List<Integer> servicesID = new ArrayList<>();

        servicesID.add(daycareService1.getDog_daycare_service_id());
        servicesID.add(daycareService2.getDog_daycare_service_id());

        DogDayCareInvoice_Dto dogDayCareInvoice_dto = new DogDayCareInvoice_Dto();

        dogDayCareInvoice_dto.setDog_daycare_invoice_date("2019-04-28 22:32:38");
        dogDayCareInvoice_dto.setDog_daycare_invoice_price(500f);
        dogDayCareInvoice_dto.setDog_daycare_invoice_duration(5f);
        dogDayCareInvoice_dto.setDog_daycare_invoice_dogdaycare_id("htovars");
        dogDayCareInvoice_dto.setDog_daycare_invoice_dog_id(2);
        dogDayCareInvoice_dto.setDog_daycare_invoice_services(servicesID);
        dogDayCareInvoice_dto.setDog_daycare_invoice_client_id("ncontreras");

        DogDaycareInvoice dogDayCareInvoice = new DogDaycareInvoice();

        dogDayCareInvoice.setDog_daycare_invoice_date(LocalDateTime.parse("2019-04-28 22:32:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        dogDayCareInvoice.setDog_daycare_invoice_price(500f);
        dogDayCareInvoice.setDog_daycare_invoice_duration(5f);
        dogDayCareInvoice.setDog_daycare_id("htovars");
        dogDayCareInvoice.setDog_id(2);

        when(proposePrice.requestPriceDogDayCareInvoice(any())).thenReturn(dogDayCareInvoice_dto);

        when(dogDaycareInvoiceRepository.saveAndFlush(any())).thenReturn(dogDayCareInvoice);

        when(dogDaycareServiceRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(daycareService1),java.util.Optional.ofNullable(daycareService2));

        when(service_invoiceRepository.save(any())).thenReturn(null);

        assertEquals("htovars",createInvoiceService.createDogDayCareInvoice(dogDayCareInvoice_dto).getDog_daycare_invoice_dogdaycare_id());

    }

    @Test
    void createWalkInvoice()
    {
        WalkInvoice_Dto walkInvoice_dto = new WalkInvoice_Dto();

        walkInvoice_dto.setClient_id("ncontreras");
        walkInvoice_dto.setDog_id(2);
        walkInvoice_dto.setDog_walker_id("htovars");
        walkInvoice_dto.setWalk_invoice_price(10000f);
        walkInvoice_dto.setWalk_invoice_status("Aceptado");

        WalkPetition walkPetition = new WalkPetition();

        walkPetition.setWalk_petition_id(1);
        walkPetition.setUser("ncontreras");
        walkPetition.setWalk_petition_walker_user("htovars");
        walkPetition.setWalk_petition_notes("notax");
        walkPetition.setWalk_petition_duration(5f);
        walkPetition.setWalk_petition_date_time(LocalDateTime.parse("2019-04-28 22:32:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        walkPetition.setDog_id(2);
        walkPetition.setWalk_petition_address("calle la cumbia");

        WalkInvoice walkInvoice = new WalkInvoice();

        walkInvoice.setWalk_invoice_id(1);
        walkInvoice.setClient_id("ncontreras");
        walkInvoice.setDog_walker_id("htovars");

        when(walkPetitionRepository.findPetitionsByDogAndByUser(anyString(),anyString())).thenReturn(walkPetition);

        doNothing().when(walkPetitionRepository).deletePetition(anyInt());

        when(walkInvoiceRepository.save(any())).thenReturn(walkInvoice);

        assertEquals("htovars",createInvoiceService.createWalkInvoice(walkInvoice_dto).getDog_walker_id());

    }
}