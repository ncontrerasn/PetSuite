package com.petsuite.Services.repository;

import com.petsuite.Services.model.WalkPetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalkPetitionRepository extends JpaRepository<WalkPetition, Integer>{

    @Query(value = "SELECT * FROM walk_petition WHERE user = ?1", nativeQuery = true)
    List<WalkPetition> findPetitionsByUser(String client_id);

    @Query(value = "SELECT * FROM walk_petition WHERE dog_id = ?1", nativeQuery = true)
    List<WalkPetition> findPetitionsByDog(String dog_id);

}