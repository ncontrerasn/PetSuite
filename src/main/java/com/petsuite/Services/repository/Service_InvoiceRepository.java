package com.petsuite.Services.repository;

import com.petsuite.Services.compositeKey.Service_Invoice_Id;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDayCareService_DogDayCareInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Service_InvoiceRepository extends JpaRepository<DogDayCareService_DogDayCareInvoice, Service_Invoice_Id>{}