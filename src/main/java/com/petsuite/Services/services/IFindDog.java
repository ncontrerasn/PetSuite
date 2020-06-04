package com.petsuite.Services.services;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Dog_Dto;

public interface IFindDog {

    public Dog_Dto find (Entero Dog_Id);

}
