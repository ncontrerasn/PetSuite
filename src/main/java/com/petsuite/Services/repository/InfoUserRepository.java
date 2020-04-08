package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoUserRepository extends JpaRepository<InfoUser, String>{}