/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.*;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.Services.services.interfaces.IRequestPetition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sergi
 */

@Service
public class RequestPetitionService implements IRequestPetition{

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    CreateInvoiceService createInvoiceService;

    @Override
    public WalkPetition_Dto createPeititon(WalkPetition_Dto walkPetition)
    {
        List<WalkPetition> allWalkPetitionsOfDog = walkPetitionRepository.findPetitionsByDog(walkPetition.getDog_id().toString());
        List<WalkInvoice> WalkInvoicesAcepted = walkInvoiceRepository.findByStatusAndUserAndDog("Aceptar",walkPetition.getUser(),walkPetition.getDog_id().toString());
        List<WalkInvoice> WalkInvoicesInProgress = walkInvoiceRepository.findByStatusAndUserAndDog("En progreso",walkPetition.getUser(),walkPetition.getDog_id().toString());

        if (allWalkPetitionsOfDog.isEmpty() && WalkInvoicesAcepted.isEmpty() && WalkInvoicesInProgress.isEmpty())
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(walkPetition.getWalk_petition_date_time(), formatter);
            dateTime.plusHours(-5);
            WalkPetition walkPetitionReal = new WalkPetition(dateTime, walkPetition.getWalk_petition_address(), walkPetition.getWalk_petition_duration(), walkPetition.getWalk_petition_notes(), walkPetition.getUser(), walkPetition.getDog_id(), null, null);
            walkPetitionReal = walkPetitionRepository.save(walkPetitionReal);

            if (walkPetitionReal != null)
                return walkPetition;
            else
                return null;
        }
        return null;
    }

    @Override
    public List<WalkPetition> myPetition(String user)
    {
        return walkPetitionRepository.findPetitionsByUser(user);
    }

    @Override
    public DogWalker_Dto walkerInPetition(Cadena user)
    {
        Optional<DogWalker> DWopt = dogWalkerRepository.findById(user.getCadena());

        DogWalker_Dto DW_DTO = new DogWalker_Dto();

        DW_DTO.setDog_walker_e_mail(DWopt.get().getE_mail());
        DW_DTO.setDog_walker_name(DWopt.get().getName());
        DW_DTO.setDog_walker_phone(DWopt.get().getPhone());
        DW_DTO.setDog_walker_score(DWopt.get().getDog_walker_score());
        DW_DTO.setUser(DWopt.get().getUser());

        return DW_DTO;
    }

    @Override
    public void deletePetition(Integer Petition_id) { walkPetitionRepository.deletePetition(Petition_id); }

    @Override
    public Dog_Dto denyOrAcceptPetition(WalkInvoice_Dto walkInvoice_Dto) {
        String status=walkInvoice_Dto.getWalk_invoice_status();
        if(status.equals("Aceptar"))
            createInvoiceService.createWalkInvoice(walkInvoice_Dto);
        else{
            WalkPetition petition= walkPetitionRepository.findPetitionsByDogAndByUser(walkInvoice_Dto.getDog_id().toString(),walkInvoice_Dto.getClient_id());
            petition.setWalk_petition_walker_user(null);
            petition.setPrice(null);
            walkPetitionRepository.save(petition);
        }
        return null;
    }


}
