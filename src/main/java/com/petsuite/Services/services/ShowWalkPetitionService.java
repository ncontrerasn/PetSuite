package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.Services.services.interfaces.IShowWalkPetition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowWalkPetitionService implements IShowWalkPetition {

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Autowired
    DogRepository dogRepository;

    @Override
    public List<WalkPetition_Dto> finPetitionByUserWithProposalPrice(Cadena user)
    {
        List<WalkPetition> lista= walkPetitionRepository.findPetitionsByUser(user.getCadena());
        List<WalkPetition_Dto> listaDtos = new ArrayList<>();
        for(int j = 0; j < lista.size(); j++)
        {
            if(lista.get(j).getPrice()!=null)
            {
                WalkPetition walkPetition = lista.get(j);
                Optional<Dog> dog = dogRepository.findById(walkPetition.getDog_id());
                listaDtos.add(new WalkPetition_Dto(walkPetition.getWalk_petition_id(), walkPetition.getWalk_petition_date_time().toString(),
                        walkPetition.getWalk_petition_address(), walkPetition.getWalk_petition_duration(), walkPetition.getWalk_petition_notes(),
                        walkPetition.getUser(), walkPetition.getDog_id(), walkPetition.getPrice(), walkPetition.getWalk_petition_walker_user(),
                        dog.get().getDog_name(), dog.get().getDog_race(), dog.get().getDog_height(), dog.get().getDog_weight(), dog.get().getDog_age(),
                        dog.get().getDog_notes()));
            }
        }
        return listaDtos;
    }
}
