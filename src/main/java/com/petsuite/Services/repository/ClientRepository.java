package com.petsuite.Services.repository;

import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, String>{}