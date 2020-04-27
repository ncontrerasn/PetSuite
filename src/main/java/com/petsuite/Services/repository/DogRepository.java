package com.petsuite.Services.repository;

import com.petsuite.Services.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface DogRepository extends JpaRepository<Dog, Integer>{

    @Query(value = "SELECT * FROM dog WHERE user = ?1", nativeQuery = true)
    List<Dog> findByUser(String user);

}