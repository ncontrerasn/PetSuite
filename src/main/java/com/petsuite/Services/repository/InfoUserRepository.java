package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.model.WalkInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface InfoUserRepository extends JpaRepository<InfoUser, String>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE info_user SET password = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateUserPassword(String password, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE info_user SET name = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateClientName(String name, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE info_user SET phone = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateClientPhone(String phone, String user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE info_user SET e_mail = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateClientEmail(String e_mail, String user);

    @Query(value = "SELECT user FROM info_user WHERE user = ?1", nativeQuery = true)
    String findUser(String user);
    
    @Query(value = "SELECT role FROM info_user WHERE user = ?1", nativeQuery = true)
    String findRoleBySuer(String user);
    
    

}