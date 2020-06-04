package com.petsuite.Services.services;

import com.petsuite.Services.services.interfaces.IFindDog;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.basics.Entero;
import java.util.Optional;

import com.petsuite.Services.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindDogService implements IFindDog{

    @Autowired
    DogRepository dogRepository;

    @Override
    public Dog_Dto find (Entero Dog_Id){

        Dog dog = dogRepository.findByDogId(Dog_Id.getEntero());

        Dog_Dto dog_dto = new Dog_Dto();

        dog_dto.setClient_id(dog.getUser());
        dog_dto.setDog_race(dog.getDog_race());
        dog_dto.setDog_notes(dog.getDog_notes());
        dog_dto.setDog_weight(dog.getDog_weight());
        dog_dto.setDog_height(dog.getDog_height());
        dog_dto.setDog_name(dog.getDog_name());
        dog_dto.setDog_age(dog.getDog_age());
        dog_dto.setDog_id(dog.getDog_id());

        return dog_dto;
    }

}
