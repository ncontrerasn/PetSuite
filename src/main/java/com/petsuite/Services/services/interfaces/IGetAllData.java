package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.*;
import java.util.List;
import java.util.Optional;

public interface IGetAllData {

    List<DogDayCare_Dto> getAllDogDayCares();
    List<Client> getAllClients();
    List<Dog> getAllDogs();
    List<DogDaycareInvoice> getAllInvoices();
    List<DogDaycareService> getAllServices();
    List<InfoUser> getAllUsers();
    List<WalkInvoice> getAllWalkInvoices();
    List<DogWalker> getAllWalkers();
    List<WalkPetition_Dto> getAllPetitions();
    Optional<DogDaycareInvoice> findInvoiceById(int id); 

}
