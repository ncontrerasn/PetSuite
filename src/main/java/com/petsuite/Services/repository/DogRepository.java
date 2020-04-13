package com.petsuite.Services.repository;

import com.petsuite.Services.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Integer>{}