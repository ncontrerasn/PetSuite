package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.basics.Cadena;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ISearchDogDayCare {

    public List<DogDayCare_Dto> searchDayCareByNameAndService(Cadena name);

}
