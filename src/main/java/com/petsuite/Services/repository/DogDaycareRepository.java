package com.petsuite.Services.repository;

import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.InfoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface DogDaycareRepository extends JpaRepository<DogDaycare, String>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_score = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateScore(float score, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_base_price = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updatePrice(float price, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_tax = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateTax(float tax, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_address = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateAddress(String address, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_type = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateType(Boolean type, String user);

    @Query(value = "SELECT user FROM info_user where name like ?1 AND role = 'ROLE_DOGDAYCARE'", nativeQuery = true)
    List<String> searchByName(String name);

    @Query(value = "SELECT user FROM dog_daycare_service where dogdaycare_service_name like ?1", nativeQuery = true)
    List<String> searchByService(String name);


}