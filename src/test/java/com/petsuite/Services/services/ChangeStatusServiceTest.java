package com.petsuite.Services.services;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ChangeStatusServiceTest {

    @InjectMocks
    ChangeStatusService changeStatusService;

    @Mock
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void endCare()
    {
        Entero ID = new Entero();

        ID.setEntero(2);

        Optional<DogDaycareInvoice> daycareInvoice = Optional.of(new DogDaycareInvoice());

        daycareInvoice.get().setDog_daycare_invoice_id(2);
        daycareInvoice.get().setDog_daycare_invoice_status("Aceptado");

        when(dogDaycareInvoiceRepository.findById(ID.getEntero())).thenReturn(daycareInvoice);

        when(dogDaycareInvoiceRepository.save(any())).thenReturn(null);

        assertEquals(true, changeStatusService.endCare(ID));

    }
}