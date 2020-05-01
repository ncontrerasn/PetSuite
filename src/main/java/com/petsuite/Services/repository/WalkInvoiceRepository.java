package com.petsuite.Services.repository;

import com.petsuite.Services.model.WalkInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalkInvoiceRepository extends JpaRepository<WalkInvoice, Integer>{

    @Query(value = "SELECT dog_id FROM walk_invoice WHERE dog_walker_id = ?1", nativeQuery = true)
    List<Integer> findByDog_walker_id(String dog_walker_id);

    @Query(value = "SELECT dog_id FROM walk_invoice WHERE walk_invoice_status = true AND dog_walker_id = ?1", nativeQuery = true)
    List<Integer> findByDog_walker_id_and_status_true(String dog_walker_id);

    @Query(value = "SELECT dog_id FROM walk_invoice WHERE walk_invoice_status = false AND dog_walker_id = ?1", nativeQuery = true)
    List<Integer> findByDog_walker_id_and_status_false(String dog_walker_id);

}