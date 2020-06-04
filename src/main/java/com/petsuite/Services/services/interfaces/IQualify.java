package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.WalkInvoice_Dto;

public interface IQualify {

    Cadena qualifyDogDayCare(DogDayCareInvoice_Dto dogDayCareInvoice_dto);
    Cadena scoreDogWalker(WalkInvoice_Dto walkInvoice_dto); //score de WalkInvoiceController

}
