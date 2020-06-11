package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.basics.Cadena;
import java.util.List;

public interface ISearchDogDayCare {

    List<DogDayCare_Dto> searchDayCareByNameAndService(Cadena name);

}
