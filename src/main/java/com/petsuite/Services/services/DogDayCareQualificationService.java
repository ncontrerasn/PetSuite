package com.petsuite.Services.services;

import com.petsuite.Services.services.interfaces.IDogDayCareQualification;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.basics.Cadena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogDayCareQualificationService implements IDogDayCareQualification {

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Override
    public Cadena qualifyDogDayCare(DogDayCareInvoice_Dto dogDayCareInvoice_dto) {
        System.out.println("Diego está intentando califiar guarderias");
        int updatedInvoice = dogDaycareInvoiceRepository.scoreDogDaycare(dogDayCareInvoice_dto.getDog_daycare_invoice_score(),
                dogDayCareInvoice_dto.getDog_daycare_invoice_id());
        if(updatedInvoice == 1){
            float score = dogDaycareInvoiceRepository.scoreAvg(dogDayCareInvoice_dto.getDog_daycare_invoice_dogdaycare_id());
            int updatedDogDaycareScore = dogDaycareRepository.updateScore(score,
                    dogDayCareInvoice_dto.getDog_daycare_invoice_dogdaycare_id());
            if(updatedDogDaycareScore == 1)
                return new Cadena("Guardería calificada correctamente");
        }
        return new Cadena("Error calificando la guardería");
    }
}
