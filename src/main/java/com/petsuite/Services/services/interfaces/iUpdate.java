package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.WalkInvoice;

import java.util.List;

public interface iUpdate {

    Client_Dto UpdateClient(Client_Dto user_dto);
    Dog_Dto UpdateDog(Dog_Dto dog);
    DogDayCare_Dto UpdateDayCare(DogDayCare_Dto user_dto);
    DogWalker_Dto UpdateDogWalker(DogWalker_Dto user_dto);
    List<WalkInvoice> updateWalkInvoiceStatus(Entero entero);

}
