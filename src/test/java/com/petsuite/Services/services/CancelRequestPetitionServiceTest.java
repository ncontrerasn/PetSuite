package com.petsuite.Services.services;

import com.petsuite.Services.dto.Cancellation_Dto;
import com.petsuite.Services.model.DogDayCareService_DogDayCareInvoice;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.Service_InvoiceRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CancelRequestPetitionServiceTest {

    @InjectMocks
    CancelRequestPetitionService cancelRequestPetitionService;

    @Mock
    InfoUserRepository infoUserRepository;

    @Mock
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @Mock
    Service_InvoiceRepository service_invoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void cancelCare()
    {
        Cancellation_Dto cancellation_dto = new Cancellation_Dto();

        cancellation_dto.setId_petition(1);
        cancellation_dto.setReasonCancellation("no me gusta");
        cancellation_dto.setUser_Cancelled("htovars");
        cancellation_dto.setUser_whoCancel("ncontreras");

        List<DogDayCareService_DogDayCareInvoice> myIntermediateList = new ArrayList<>();

        DogDaycareInvoice dogDayCareInvoice = new DogDaycareInvoice();

        dogDayCareInvoice.setDog_daycare_invoice_date(LocalDateTime.parse("2019-04-28 22:32:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        dogDayCareInvoice.setDog_daycare_invoice_price(500f);
        dogDayCareInvoice.setDog_daycare_invoice_duration(5f);
        dogDayCareInvoice.setDog_daycare_id("htovars");
        dogDayCareInvoice.setDog_id(2);

        DogDayCareService_DogDayCareInvoice myIntermediate = new DogDayCareService_DogDayCareInvoice();

        myIntermediate.setDogDaycareInvoice(dogDayCareInvoice);

        myIntermediateList.add(myIntermediate);

        when(infoUserRepository.findRoleBySuer(anyString())).thenReturn("ncontreras");

        when(service_invoiceRepository.findIntermediateServicesIvoiceByInvoiceId(anyInt())).thenReturn(myIntermediateList);

        doNothing().when(service_invoiceRepository).delete(any());

        when(dogDaycareInvoiceRepository.findById(anyInt())).thenReturn(java.util.Optional.of(dogDayCareInvoice));

        doNothing().when(dogDaycareInvoiceRepository).delete(any());

        when(dogDaycareInvoiceRepository.numberById(anyInt())).thenReturn(0);

        assertEquals(true,cancelRequestPetitionService.cancelCare(cancellation_dto));
    }

    @Test
    void cancelWalk()
    {
        Cancellation_Dto cancellation_dto = new Cancellation_Dto();

        cancellation_dto.setId_petition(1);
        cancellation_dto.setReasonCancellation("no me gusta");
        cancellation_dto.setUser_Cancelled("htovars");
        cancellation_dto.setUser_whoCancel("ncontreras");

        WalkInvoice walkInvoice = new WalkInvoice();

        walkInvoice.setWalk_invoice_date(LocalDateTime.parse("2019-04-28 22:32:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        walkInvoice.setWalk_invoice_price(500f);
        walkInvoice.setWalk_invoice_duration(5f);
        walkInvoice.setWalk_invoice_id(1);
        walkInvoice.setDog_id(2);
        walkInvoice.setClient_id("htovars");

        when(infoUserRepository.findRoleBySuer(anyString())).thenReturn("ncontreras");

        when(walkInvoiceRepository.findById(anyInt())).thenReturn(java.util.Optional.of(walkInvoice));

        doNothing().when(walkInvoiceRepository).delete(any());

        when(walkInvoiceRepository.numberById(anyInt())).thenReturn(0);

        assertEquals(true,cancelRequestPetitionService.cancelWalk(cancellation_dto));

    }
}