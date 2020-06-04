/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;

/**
 *
 * @author sergi
 */
public interface IRequestPetition {
    WalkPetition_Dto createPeititon( WalkPetition_Dto walkPetition); //create de WalkpetitionController
    DogDayCareInvoice_Dto createDogDaycareInvoice(DogDayCareInvoice_Dto dogDaycareInovice); //load de DogDayCareInvoiceController
    
    
}
