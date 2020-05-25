package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DogDayCareInvoiceControllerTest {

    @InjectMocks
    DogDayCareInvoiceController dogDayCareInvoiceController;

    @Mock
    DogRepository dogRepository;

    @Mock
    DogDaycareRepository dogDaycareRepository;

    @Mock
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Mock
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @BeforeEach
    void setUp() { MockitoAnnotations.initMocks(this); }

    /*@Test
    void createDogDaycareInvoice() {
        List<Integer> servicios = new ArrayList();
        servicios.add(1);
        servicios.add(2);

        DogDayCareInvoice_Dto dogDaycareInovice = new DogDayCareInvoice_Dto("2020-04-28 16:32:38",
                (float)5, null, "false", "jose",
                "diego", 1, servicios, null,
                null, null, null);

        DogDaycare dogDaycare = dogDaycareRepository.findById(dogDaycareInovice.getDog_daycare_invoice_dogdaycare_id()).get();

        Float price = dogDaycareInovice.getDog_daycare_invoice_duration() * dogDaycare.getDog_daycare_base_price();

    }*/
}