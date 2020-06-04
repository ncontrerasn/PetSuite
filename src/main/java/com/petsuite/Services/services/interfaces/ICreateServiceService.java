/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCare_Service_Dto;

/**
 *
 * @author sergi
 */
public interface ICreateServiceService {
    public DogDayCare_Service_Dto createService(DogDayCare_Service_Dto care_Service_Dto);//load de DogDayCareService
    
}
