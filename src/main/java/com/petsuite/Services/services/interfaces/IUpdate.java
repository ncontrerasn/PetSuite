package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.Dog_Dto;

public interface IUpdate {

    Client_Dto UpdateClient(Client_Dto user_dto);
    Dog_Dto UpdateDog(Dog_Dto dog);
    DogDayCare_Dto UpdateDayCare(DogDayCare_Dto user_dto);
    DogWalker_Dto UpdateDogWalker(DogWalker_Dto user_dto);

}
