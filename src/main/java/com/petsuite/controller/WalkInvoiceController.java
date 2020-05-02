package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.basics.Cadena;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/walkinvoices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class WalkInvoiceController {

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @GetMapping("/all")
    public List<WalkInvoice> getAllInvoices() {
        return walkInvoiceRepository.findAll();
    }

    @PostMapping("/create")
    public WalkInvoice createInvoice(@Valid @RequestBody WalkInvoice_Dto walkinvoice){

        WalkPetition WalkPetitions = walkPetitionRepository.findPetitionsByDogAndByUser(walkinvoice.getDog_id().toString(),walkinvoice.getClient_id());

        if (WalkPetitions!=null) {

            WalkInvoice walkinvoiceReal = new WalkInvoice(WalkPetitions.getWalk_petition_date_time(),
                    WalkPetitions.getWalk_petition_address(), WalkPetitions.getWalk_petition_duration(),
                    WalkPetitions.getWalk_petition_notes(), WalkPetitions.getUser(), WalkPetitions.getDog_id(),
                    walkinvoice.getDog_walker_id(), walkinvoice.getWalk_invoice_price(),walkinvoice.getWalk_invoice_status());

            walkPetitionRepository.deletePetition(WalkPetitions.getWalk_petition_id());

            walkinvoiceReal = walkInvoiceRepository.save(walkinvoiceReal);

            if (walkinvoiceReal != null)
                return walkinvoiceReal;
            else
                return null;
        }else{
            return null;
        }
    }

}
