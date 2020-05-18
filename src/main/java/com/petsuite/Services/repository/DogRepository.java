package com.petsuite.Services.repository;

import com.petsuite.Services.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer>{

    @Query(value = "SELECT * FROM dog where user=?1", nativeQuery = true)
    List<Dog> findByUser(String user);
    
     @Query(value = "SELECT * FROM dog where dog_id=?1", nativeQuery = true)
    Dog findByDogId(Integer dog_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog SET dog_age = ?1 WHERE dog_id = ?2", nativeQuery = true)
    Integer updateAge(Integer dog_age, Integer dog_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog SET dog_height = ?1 WHERE dog_id = ?2", nativeQuery = true)
    Integer updateHeight(float dog_height, Integer dog_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog SET dog_name = ?1 WHERE dog_id = ?2", nativeQuery = true)
    Integer updateName(String dog_name, Integer dog_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog SET dog_notes = ?1 WHERE dog_id = ?2", nativeQuery = true)
    Integer updateNotes(String dog_notes, Integer dog_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog SET dog_race = ?1 WHERE dog_id = ?2", nativeQuery = true)
    Integer updateRace(String dog_race, Integer dog_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE dog SET dog_weight = ?1 WHERE dog_id = ?2", nativeQuery = true)
    Integer updateWeight(float dog_weight, Integer dog_id);


}