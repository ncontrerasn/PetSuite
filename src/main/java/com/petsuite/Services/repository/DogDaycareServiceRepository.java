package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogDaycareService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import com.petsuite.Services.model.WalkInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface DogDaycareServiceRepository extends JpaRepository<DogDaycareService, Integer>{

 @Query(value = "SELECT * FROM dog_daycare_service WHERE user = ?1", nativeQuery = true)
  List<DogDaycareService> findMyServicesByUser(String user);
  
}