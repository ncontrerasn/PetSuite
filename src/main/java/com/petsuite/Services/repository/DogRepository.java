package com.petsuite.Services.repository;

import com.petsuite.Services.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface DogRepository extends JpaRepository<Dog, Integer>{

    @Query(value = "SELECT * FROM dog where user=?1", nativeQuery = true)
    List<Dog> findByUser(String user);
    
     @Query(value = "SELECT * FROM dog where dog_id=?1", nativeQuery = true)
    Dog findByDogId(Integer dog_id);


}