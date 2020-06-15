package com.petsuite.Services.services;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ChangeStatusServiceTest {

    @InjectMocks
    ChangeStatusRequestPetitionService changeStatusService;

    @Mock
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateWalkInvoiceStatus()
    {
        Entero ID = new Entero();

        ID.setEntero(2);

        Optional<WalkInvoice> walkInvoice = Optional.of(new WalkInvoice());

        walkInvoice.get().setWalk_invoice_id(2);
        walkInvoice.get().setWalk_invoice_status("Aceptar");

        when(walkInvoiceRepository.findById(ID.getEntero())).thenReturn(walkInvoice);

        when(walkInvoiceRepository.save(any())).thenReturn(null);

        assertEquals("En progreso", changeStatusService.updateWalkInvoiceStatus(ID).get().getWalk_invoice_status());

    }

    @Test
    void updateCareInvoiceStatus()
    {
        Entero ID = new Entero();

        ID.setEntero(2);

        Optional<DogDaycareInvoice> daycareInvoice = Optional.of(new DogDaycareInvoice());

        daycareInvoice.get().setDog_daycare_invoice_id(2);
        daycareInvoice.get().setDog_daycare_invoice_status("Aceptado");

        when(dogDaycareInvoiceRepository.findById(ID.getEntero())).thenReturn(daycareInvoice);

        Optional<DogDaycareInvoice> daycareInvoice_ret = Optional.of(new DogDaycareInvoice());

        daycareInvoice_ret.get().setDog_daycare_invoice_id(2);
        daycareInvoice_ret.get().setDog_daycare_invoice_status("En progreso");

        when(dogDaycareInvoiceRepository.save(any())).thenReturn(daycareInvoice_ret.get());

        assertEquals("En progreso", changeStatusService.updateCareInvoiceStatus(ID).getDog_daycare_invoice_status());

    }
}