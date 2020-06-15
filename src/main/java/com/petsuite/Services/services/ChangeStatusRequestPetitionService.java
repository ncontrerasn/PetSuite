package com.petsuite.Services.services;

import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petsuite.Services.services.interfaces.IChangeStatusRequestPetition;
import java.util.List;

@Service
public class ChangeStatusRequestPetitionService implements IChangeStatusRequestPetition{
    
    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;
    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Override
    public  Optional<WalkInvoice> updateWalkInvoiceStatus(Entero entero)
    {
        Optional<WalkInvoice> walkInvoice = walkInvoiceRepository.findById(entero.getEntero());
        String status = walkInvoice.get().getWalk_invoice_status();

        switch(status){
            case "Aceptar":

                walkInvoice.get().setWalk_invoice_status("En progreso");
                walkInvoiceRepository.save(walkInvoice.get());
                if(walkInvoice.get().getWalk_invoice_status() == "En progreso")
                    return walkInvoice;
                break;

            case "En progreso":
                walkInvoice.get().setWalk_invoice_status("Terminado");
                walkInvoiceRepository.save(walkInvoice.get());
                if(walkInvoice.get().getWalk_invoice_status() == "Terminado")
                    return walkInvoice;
                break;
        }
        return null;
    }

    @Override
    public DogDaycareInvoice updateCareInvoiceStatus(Entero entero) {
        Optional<DogDaycareInvoice> invoice = dogDaycareInvoiceRepository.findById(entero.getEntero());
        String status = invoice.get().getDog_daycare_invoice_status();

        switch(status){
            case "Aceptado":

                invoice.get().setDog_daycare_invoice_status("En progreso");
               return dogDaycareInvoiceRepository.save(invoice.get());

            case "En progreso":
                invoice.get().setDog_daycare_invoice_status("Terminado");
               return dogDaycareInvoiceRepository.save(invoice.get());
                
        }
        return null;
    }
    
}
