/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.WalkInvoice;

/**
 *
 * @author sergi
 */
public interface IAcceptPrice {
    Dog_Dto denyOrAcceptPetition(WalkInvoice_Dto walkInvoice_Dto);//acceptOrDeny WalkPetitionController
    DogDayCareInvoice_Dto createDogDayCareInvoice(DogDayCareInvoice_Dto dogDaycareInovice);//load deDogDayCareInvoiceController
    WalkInvoice createInvoice(WalkInvoice_Dto walkinvoice);//create de WalkInvoiceController
    
    
}
