package com.petsuite.Services.repository;

import com.petsuite.Services.compositeKey.Service_Invoice_Id;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDayCareService_DogDayCareInvoice;
import com.petsuite.Services.model.DogDaycareService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Service_InvoiceRepository extends JpaRepository<DogDayCareService_DogDayCareInvoice, Service_Invoice_Id>{
@Query(value = "SELECT * FROM service_invoice WHERE dog_daycare_invoice_id = ?1", nativeQuery = true)
  List<DogDayCareService_DogDayCareInvoice> findIntermediateServicesIvoiceByInvoiceId(Integer dog_daycare_invoice_id );




}