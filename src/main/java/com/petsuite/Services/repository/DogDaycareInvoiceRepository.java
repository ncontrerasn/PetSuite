package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogDaycareInvoice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DogDaycareInvoiceRepository extends JpaRepository<DogDaycareInvoice, Integer>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare_invoice SET dog_daycare_score = ?1 WHERE dog_daycare_invoice_id = ?2", nativeQuery = true)
    Integer scoreDogDaycare(float score, int invoice_id);

    @Query(value = "SELECT AVG(dog_daycare_score) FROM dog_daycare_invoice WHERE dog_daycare_id = ?1", nativeQuery = true)
    Float scoreAvg(String dog_daycare_id);
    
    @Query(value = "SELECT * FROM dog_daycare_invoice WHERE dog_daycare_id = ?1", nativeQuery = true)
    List<DogDaycareInvoice> findInvoicesByDogDayCare(String dog_daycare_id);

    @Query(value = "SELECT dogdaycare_service_name FROM dog_daycare_service NATURAL JOIN service_invoice NATURAL JOIN dog_daycare_invoice  WHERE dog_daycare_invoice_id = ?1", nativeQuery = true)
    List<String> findNameServicesByInvoiceId(Integer invoice_id);

    @Query(value = "SELECT dog_name FROM dog NATURAL JOIN dog_daycare_invoice  WHERE dog_daycare_invoice_id = ?1", nativeQuery = true)
    String findDogNameByInvoiceId(Integer invoice_id);
    
    @Query(value = "SELECT * FROM dog_daycare_invoice WHERE dog_daycare_id = ?1 AND dog_daycare_invoice_status = ?2", nativeQuery = true)
    List<DogDaycareInvoice> findInvoicesByDogDayCareAndStatus(String dog_daycare_id, String status);

    @Query(value = "SELECT * FROM dog_daycare_invoice WHERE client_id = ?1 AND dog_daycare_invoice_status = ?2", nativeQuery = true)
    List<DogDaycareInvoice> findInvoicesByClientAndStatus(String client_id, String status);

    @Query(value = "SELECT count(*) dog_daycare_invoice_id FROM dog_daycare_invoice WHERE dog_daycare_invoice_id= ?1", nativeQuery = true)
    Integer numberById(Integer invoiceId);

    @Query(value = "SELECT dog_id FROM dog_daycare_invoice WHERE dog_daycare_id = ?1 AND dog_daycare_invoice_status = ?2", nativeQuery = true)
    List<Integer> getCurrentDogListInChargeOfDogDayCare(String client_id, String status);

}