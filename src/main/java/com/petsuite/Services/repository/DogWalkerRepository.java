package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogWalkerRepository extends JpaRepository<DogWalker, Float>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog_walker SET dog_walker_score = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateScore(float score, String user);
}