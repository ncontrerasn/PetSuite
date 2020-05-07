package com.petsuite.Services.repository;

import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface InfoUserRepository extends JpaRepository<InfoUser, String>{

    @Transactional
    @Modifying
    @Query(value = "UPDATE info_user SET password = ?1 WHERE user = ?2", nativeQuery = true)
    Integer updateUserPassword(String password, String user);

}