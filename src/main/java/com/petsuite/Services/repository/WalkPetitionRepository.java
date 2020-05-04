package com.petsuite.Services.repository;

import com.petsuite.Services.model.WalkPetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface WalkPetitionRepository extends JpaRepository<WalkPetition, Integer>{

    @Query(value = "SELECT * FROM walk_petition WHERE user = ?1", nativeQuery = true)
    List<WalkPetition> findPetitionsByUser(String client_id);

    @Query(value = "SELECT * FROM walk_petition WHERE dog_id = ?1", nativeQuery = true)
    List<WalkPetition> findPetitionsByDog(String dog_id);

    @Query(value = "SELECT * FROM walk_petition WHERE walk_petition_id = ?1", nativeQuery = true)
    WalkPetition findPetitionsById(Integer walk_petition_id);

    @Query(value = "SELECT * FROM walk_petition WHERE dog_id = ?1 AND user = ?2", nativeQuery = true)
    WalkPetition findPetitionsByDogAndByUser(String dog_id, String client_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM walk_petition WHERE walk_petition_id = ?1", nativeQuery = true)
    void deletePetition(Integer walk_petition_id);

}