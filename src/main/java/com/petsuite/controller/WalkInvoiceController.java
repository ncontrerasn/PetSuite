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
@RequestMapping("/api/walkinvoice")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class WalkInvoiceController {

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @GetMapping("/all")
    public List<WalkInvoice> getAllInvoices() {
        return walkInvoiceRepository.findAll();
    }

    @PostMapping("/create")
    public WalkInvoice createInvoice(@Valid @RequestBody WalkInvoice_Dto walkinvoice){

        List<WalkPetition> WalkPetitions = walkPetitionRepository.findPetitionsByDogAndByUser(walkinvoice.getDog_id().toString(),walkinvoice.getClient_id());

        if (!WalkPetitions.isEmpty()) {

            WalkInvoice walkinvoiceReal = new WalkInvoice(WalkPetitions.get(0).getWalk_petition_date_time(),
                    WalkPetitions.get(0).getWalk_petition_address(), WalkPetitions.get(0).getWalk_petition_duration(),
                    WalkPetitions.get(0).getWalk_petition_notes(), WalkPetitions.get(0).getUser(), WalkPetitions.get(0).getDog_id(),
                    walkinvoice.getDog_walker_id(), walkinvoice.getWalk_invoice_price(), walkinvoice.isWalk_invoice_status());

            walkPetitionRepository.deletePetition(WalkPetitions.get(0).getWalk_petition_id());

            walkinvoiceReal = walkInvoiceRepository.save(walkinvoiceReal);

            if (walkinvoiceReal != null)
                return walkinvoiceReal;
            else
                return null;
        }else
            return null;
    }

    @PostMapping("/score")//para pedir otro paseo debe estar calificado al paseador. Pero para comenzar un paseo, se hace un recibo con status 0, cuando se califica al paseador, se pone status 1, cuando se califica ya es porque se ha terminado el paseo
    public Cadena scoreDogWalker(@Valid @RequestBody WalkInvoice_Dto walkInvoice_dto){//se podria hacer un dto solo con id factura, id paseador y puntaje
        int updatedInvoice = walkInvoiceRepository.scoreWalker(walkInvoice_dto.getWalker_score(), walkInvoice_dto.getWalk_invoice_id());
        if(updatedInvoice == 1){
            float score = walkInvoiceRepository.scoreAvg(walkInvoice_dto.getDog_walker_id());
            int updatedWalkerScore = dogWalkerRepository.updateScore(score, walkInvoice_dto.getDog_walker_id());
            if(updatedWalkerScore == 1)
                return new Cadena("Paseador calificado correctamente");
        }
        return new Cadena("Error calificando al paseador");
    }

}
