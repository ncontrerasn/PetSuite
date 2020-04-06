package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogWalkerRepository extends JpaRepository<DogWalker, String>{}