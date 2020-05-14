package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogDaycare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DogDaycareRepository extends JpaRepository<DogDaycare, String>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_score = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateScore(float score, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_address = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateAddress(String address, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_daycare SET dog_daycare_type = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateType(boolean type, String user);

}