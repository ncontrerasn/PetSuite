package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.Dog_Dto;

public interface IRegister {

     DogWalker_Dto createWalker(DogWalker_Dto dogWalker);//load de Walker
     DogDayCare_Dto createDogDaycare(DogDayCare_Dto dogDaycare); //load de DogDayCare
     Client_Dto createClient(  Client_Dto client); //load de Client
     Dog_Dto createDog( Dog_Dto dog);//Register Dog

}
