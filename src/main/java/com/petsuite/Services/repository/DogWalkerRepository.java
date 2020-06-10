package com.petsuite.Services.repository;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.model.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DogWalkerRepository extends JpaRepository<DogWalker, String>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_walker SET dog_walker_score = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateScore(float score, String user);
    
    @Query(value = "SELECT * FROM info_user natural join dog_walker where user =?1", nativeQuery = true)
    List<DogWalker> findWalkerByUser(String user);
    
    
    

}