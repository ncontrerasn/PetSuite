/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCare_Service_Dto;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.services.interfaces.ICreateServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergi
 */
@Service
public class CreateService implements ICreateServiceService{

    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Override
    public DogDayCare_Service_Dto createService(DogDayCare_Service_Dto care_Service_Dto)
    {
        DogDaycareService daycareService= new DogDaycareService(null, care_Service_Dto.getDogdaycare_Service_Name(), care_Service_Dto.getDogdaycare_Service_Description(), care_Service_Dto.getDogdaycare_Service_Price(), care_Service_Dto.getDogdaycare_Service_ClientId(), null,null);

        if(daycareService!=null){
            dogDaycareServiceRepository.save(daycareService);
            return care_Service_Dto;
        }
        return null;
    }
}
