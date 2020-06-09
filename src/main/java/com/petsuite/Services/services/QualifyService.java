package com.petsuite.Services.services;

import com.petsuite.Services.basics.Flotante;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petsuite.Services.services.interfaces.IQualify;

import java.util.Optional;

@Service
public class QualifyService implements IQualify {

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Override
    public Cadena qualifyDogDayCare(DogDayCareInvoice_Dto dogDayCareInvoice_dto)
    {
        int updatedInvoice = dogDaycareInvoiceRepository.scoreDogDaycare(dogDayCareInvoice_dto.getDog_daycare_invoice_score(),
                dogDayCareInvoice_dto.getDog_daycare_invoice_id());
        if(updatedInvoice == 1)
        {
            float score = dogDaycareInvoiceRepository.scoreAvg(dogDayCareInvoice_dto.getDog_daycare_invoice_dogdaycare_id());
            int updatedDogDaycareScore = dogDaycareRepository.updateScore(score,
                    dogDayCareInvoice_dto.getDog_daycare_invoice_dogdaycare_id());
            if(updatedDogDaycareScore == 1)
                return new Cadena("Guardería calificada correctamente");
        }
        return new Cadena("Error calificando la guardería");
    }

    @Override
    public Cadena scoreDogWalker(WalkInvoice_Dto walkInvoice_dto)
    {
        int updatedInvoice = walkInvoiceRepository.scoreWalker(walkInvoice_dto.getWalker_score(), walkInvoice_dto.getWalk_invoice_id());
        if(updatedInvoice == 1)
        {
            float score = walkInvoiceRepository.scoreAvg(walkInvoice_dto.getDog_walker_id());
            int updatedWalkerScore = dogWalkerRepository.updateScore(score, walkInvoice_dto.getDog_walker_id());
            if(updatedWalkerScore == 1)
                return new Cadena("Paseador calificado correctamente");
        }
        return new Cadena("Error calificando al paseador");
    }

    @Override
    public Flotante getQualifications(Cadena cadena)
    {
        Optional<DogWalker> dogWalker= dogWalkerRepository.findById(cadena.getCadena());
        return new Flotante(dogWalker.get().getDog_walker_score());
    }
}
