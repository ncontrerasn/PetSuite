/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author sergi
 */
public interface IRegister {
     public DogWalker_Dto createWalker(DogWalker_Dto dogWalker);//load de Walker
     public DogDayCare_Dto createDogDaycare(DogDayCare_Dto dogDaycare); //load de DogDayCare
     public Client_Dto createClient(  Client_Dto client); //load de Client
      public Dog_Dto createDog( Dog_Dto dog);//Register Dog

    
}
