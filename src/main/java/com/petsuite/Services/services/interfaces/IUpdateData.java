/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;

/**
 *
 * @author sergi
 */
public interface IUpdateData {
    public DogDayCare_Dto updateAllDogDayCare(DogDayCare_Dto user_dto);
    
    public Integer updateTaxDogDayCare(String user, Float tax);
    public Integer updatePriceDogDayCare(String user, Float price);
    public Integer updateMail(String user, String Mail);
    public Integer updatePhone(String user, String Phone);
    public Integer updateName(String user, String name);
    public Integer updateUserPassword(String user, String password);
    public Integer updateAddress(String user, String address);
    public Client_Dto updateAllClient(Client_Dto user_dto);
    public DogWalker_Dto updateAllDogWalker(DogWalker_Dto user_dto);
}
