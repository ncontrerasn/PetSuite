package com.petsuite.Services.repository;

import com.petsuite.Services.model.WalkInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalkInvoiceRepository extends JpaRepository<WalkInvoice, Integer>{

    @Query(value = "SELECT * FROM walk_invoice WHERE dog_walker_id = ?1", nativeQuery = true)
    List<Integer> findByDog_walker_id(String dog_walker_id);

}