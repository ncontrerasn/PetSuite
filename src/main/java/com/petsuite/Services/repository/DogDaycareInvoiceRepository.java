package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogDaycareInvoice;
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

}