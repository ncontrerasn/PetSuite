package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkPetition;
import java.util.List;

public interface IFindDog {

    Dog_Dto find (Entero Dog_Id);
    List<Dog> myDogList(Cadena user);
    List<Dog> walkerDogList(Cadena dogWalker);
    List<Dog> findDogsByWalkerAndStatusAccepted(Cadena cadena);
    List<WalkPetition> finPetitionByDog(Cadena dog);

}
