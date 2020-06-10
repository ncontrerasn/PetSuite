/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.Services.services.interfaces.IProposePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sergi
 */

@Service
public class ProposePrice implements IProposePrice{

    @Autowired
    DogRepository dogRepository;

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Override
    public Dog_Dto proposePrice(WalkPetition_Dto walkPetition_Dto)
    {
        WalkPetition petition= walkPetitionRepository.findPetitionsById(walkPetition_Dto.getWalk_petition_id());
        petition.setPrice(walkPetition_Dto.getPrecio_proposal());
        petition.setWalk_petition_walker_user(walkPetition_Dto.getWalk_petition_walker_user());
        walkPetitionRepository.save(petition);
        return null;
    }

    @Override
    public DogDayCareInvoice_Dto requestPriceDogDayCareInvoice(DogDayCareInvoice_Dto dogDaycareInovice)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dogDaycareInovice.getDog_daycare_invoice_date(), formatter);
        float dogWeight = dogRepository.findByDogId(dogDaycareInovice.getDog_daycare_invoice_dog_id()).getDog_weight();
        DogDaycare dogDaycare = dogDaycareRepository.findById(dogDaycareInovice.getDog_daycare_invoice_dogdaycare_id()).get();
        //precio base por la estadía
        Float price = dogDaycareInovice.getDog_daycare_invoice_duration() * dogDaycare.getDog_daycare_base_price();
        //perro pesado es mayor o igual que 28 kg
        if(dogWeight >= 28)
            price += dogDaycareInovice.getDog_daycare_invoice_duration() * dogDaycare.getDog_daycare_tax();
        //más el precio por los servicios
        for (int i = 0; i < dogDaycareInovice.getDog_daycare_invoice_services().size(); i++)
            price += dogDaycareServiceRepository.findById(
                    dogDaycareInovice.getDog_daycare_invoice_services().get(i)).get().getDogdaycare_Service_Price();
        //Asigna un precio
        dogDaycareInovice.setDog_daycare_invoice_price(price);
        return dogDaycareInovice;
    }
}
