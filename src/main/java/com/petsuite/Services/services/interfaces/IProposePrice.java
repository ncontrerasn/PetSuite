package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.WalkPetition;

public interface IProposePrice {

    WalkPetition proposePrice(WalkPetition_Dto walkPetition_Dto);//propose de walkpetitionController
    DogDayCareInvoice_Dto requestPriceDogDayCareInvoice(DogDayCareInvoice_Dto dogDaycareInovice);// conlsutPrice de DogDayCareInvoiceController
    
}
