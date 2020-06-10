package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.WalkInvoice;

public interface ICreateInvoiceService {

    DogDayCareInvoice_Dto createDogDayCareInvoice(DogDayCareInvoice_Dto dogDaycareInovice);
    WalkInvoice createWalkInvoice(WalkInvoice_Dto walkinvoice);

}
