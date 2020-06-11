package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.*;
import com.petsuite.Services.repository.*;
import com.petsuite.Services.services.interfaces.IGetAllData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetAllDataService implements IGetAllData {

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Autowired
    InfoUserRepository infoUserRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Override
    public List<DogDayCare_Dto> getAllDogDayCares()
    {
        List<DogDaycare> lista=dogDaycareRepository.findAll();
        List<DogDayCare_Dto> listaEnviar= new ArrayList<>();
        for (int i = 0; i < lista.size(); i++)
        {
            DogDayCare_Dto  guarderia = new DogDayCare_Dto(lista.get(i).getE_mail(), lista.get(i).getDog_daycare_address(), lista.get(i).getDog_daycare_type(), lista.get(i).getPhone(), lista.get(i).getDog_daycare_score(), lista.get(i).getName(), lista.get(i).getDog_daycare_base_price(), lista.get(i).getDog_daycare_tax());
            guarderia.setUser(lista.get(i).getUser());
            listaEnviar.add(guarderia);
        }
        return listaEnviar;
    }

    @Override
    public List<Client> getAllClients()
    {
        return clientRepository.findAll();
    }

    @Override
    public List<Dog> getAllDogs() { return dogRepository.findAll(); }

    @Override
    public List<DogDaycareInvoice> getAllInvoices() { return dogDaycareInvoiceRepository.findAll(); }

    @Override
    public List<DogDaycareService> getAllServices() { return dogDaycareServiceRepository.findAll(); }

    @Override
    public List<InfoUser> getAllUsers() { return infoUserRepository.findAll(); }

    @Override
    public List<WalkInvoice> getAllWalkInvoices() { return walkInvoiceRepository.findAll(); }

    @Override
    public List<DogWalker> getAllWalkers() { return dogWalkerRepository.findAll(); }

    @Override
    public List<WalkPetition_Dto> getAllPetitions()
    {
        List<WalkPetition> lista= walkPetitionRepository.findAll();
        List<WalkPetition_Dto> listaDtos = new ArrayList<>();
        for(int j = 0; j < lista.size(); j++)
        {
            WalkPetition walkPetition = lista.get(j);
            Optional<Dog> dog = dogRepository.findById(walkPetition.getDog_id());
            if(lista.get(j).getPrice() == null)
                listaDtos.add(new WalkPetition_Dto(walkPetition.getWalk_petition_id(), walkPetition.getWalk_petition_date_time().toString(),
                        walkPetition.getWalk_petition_address(), walkPetition.getWalk_petition_duration(), walkPetition.getWalk_petition_notes(),
                        walkPetition.getUser(), walkPetition.getDog_id(), walkPetition.getPrice(), walkPetition.getWalk_petition_walker_user(),
                        dog.get().getDog_name(), dog.get().getDog_race(), dog.get().getDog_height(), dog.get().getDog_weight(), dog.get().getDog_age(),
                        dog.get().getDog_notes()));
        }
        return listaDtos;
    }

}
