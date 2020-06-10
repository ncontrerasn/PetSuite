/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;

/**
 *
 * @author sergi
 */
public interface IProposePrice {

    Dog_Dto proposePrice(WalkPetition_Dto walkPetition_Dto);//propose de walkpetitionController
    DogDayCareInvoice_Dto requestPriceDogDayCareInvoice(DogDayCareInvoice_Dto dogDaycareInovice);// conlsutPrice de DogDayCareInvoiceController
    
}
