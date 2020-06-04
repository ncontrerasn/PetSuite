package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.basics.Cadena;

public interface IDogDayCareQualification {

    Cadena qualifyDogDayCare(DogDayCareInvoice_Dto dogDayCareInvoice_dto);

}
