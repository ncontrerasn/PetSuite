package com.petsuite.Services.repository;

import com.petsuite.Services.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String>{}