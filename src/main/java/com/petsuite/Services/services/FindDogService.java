package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.Services.services.interfaces.IFindDog;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.basics.Entero;
import java.util.ArrayList;
import java.util.List;
import com.petsuite.Services.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindDogService implements IFindDog{

    @Autowired
    DogRepository dogRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Override
    public Dog_Dto find (Entero Dog_Id)
    {
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

    @Override
    public List<Dog> myDogList(Cadena user) { return dogRepository.findByUser(user.getCadena()); }

    @Override
    public List<Dog> walkerDogList(Cadena dogWalker)
    {
        List<Dog> dogs = new ArrayList<>();
        List<Integer> dogs_ids = walkInvoiceRepository.findByDog_walker_id(dogWalker.getCadena());

        for(int i = 0; i < dogs_ids.size() ; i++)
        {
            dogs.add(dogRepository.findByDogId(dogs_ids.get(i)));
        }
        return dogs;
    }

    @Override
    public List<Dog> findDogsByWalkerAndStatusAccepted(Cadena cadena)
    {
        List<Dog> dogs = new ArrayList<>();
        List<Integer> accepted = walkInvoiceRepository.findByWalkerAndStatusAccepted(cadena.getCadena(), "En progreso");
        for(int i = 0; i < accepted.size(); i++)
            dogs.add(dogRepository.findByDogId(accepted.get(i)));
        return dogs;
    }

    @Override
    public List<WalkPetition> finPetitionByDog(Cadena dog) { return walkPetitionRepository.findPetitionsByDog(dog.getCadena()); }

}
