package com.petsuite.repository;

import com.petsuite.model.DogWalker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogWalkerRepository extends JpaRepository<DogWalker, Integer>{}