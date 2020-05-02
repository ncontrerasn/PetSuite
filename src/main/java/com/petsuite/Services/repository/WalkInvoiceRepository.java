package com.petsuite.Services.repository;

import com.petsuite.Services.model.WalkInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface WalkInvoiceRepository extends JpaRepository<WalkInvoice, Integer>{

    @Query(value = "SELECT dog_id FROM walk_invoice WHERE dog_walker_id = ?1", nativeQuery = true)
    List<Integer> findByDog_walker_id(String dog_walker_id);

    @Query(value = "SELECT dog_id FROM walk_invoice WHERE walk_invoice_status = true AND dog_walker_id = ?1", nativeQuery = true)
    List<Integer> findByDog_walker_id_and_status_true(String dog_walker_id);

    @Query(value = "SELECT dog_id FROM walk_invoice WHERE walk_invoice_status = false AND dog_walker_id = ?1", nativeQuery = true)
    List<Integer> findByDog_walker_id_and_status_false(String dog_walker_id);

    @Query(value = "SELECT * FROM walk_invoice WHERE walk_invoice_status = ?1", nativeQuery = true)
    List<WalkInvoice> findByStatus(String cadena);

    @Query(value = "SELECT * FROM walk_invoice WHERE dog_walker_id = ?1", nativeQuery = true)//manda todos los recibos
    List<WalkInvoice> findByWalker(String cadena);

    @Query(value = "SELECT * FROM walk_invoice WHERE dog_walker_id = ?1 AND walk_invoice_status = ?2", nativeQuery = true)//paseos filtrados por estado
    List<WalkInvoice> findByWalkerAndStatus(String walker, String status);

    @Query(value = "SELECT dog_id FROM walk_invoice WHERE dog_walker_id = ?1 AND walk_invoice_status = ?2", nativeQuery = true)//paseos filtrados por estado
    List<Integer> findByWalkerAndStatusAccepted(String walker, String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE walk_invoice SET walker_score = ?1 WHERE walk_invoice_id = ?2", nativeQuery = true)
    Integer scoreWalker(float score, int walk_invoice_id);

    @Query(value = "SELECT AVG(walker_score) FROM walk_invoice WHERE dog_walker_id = ?1", nativeQuery = true)
    Float scoreAvg(String walker_id);
}