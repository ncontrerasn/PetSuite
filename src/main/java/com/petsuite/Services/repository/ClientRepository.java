package com.petsuite.Services.repository;

import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, String>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE client SET client_address = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateAddressByUser(String client_address, String user);

    @Query(value = "SELECT client_address FROM client WHERE user = ?1", nativeQuery = true)
    String selectAddressFromUser(String user);
    
    @Query(value = "SELECT * FROM info_user natural join client where user = ?1", nativeQuery = true)
    List<Client> findClientbyUser(String user);
    

}