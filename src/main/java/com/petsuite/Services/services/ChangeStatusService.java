/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services;

import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.basics.Entero;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petsuite.Services.services.interfaces.IChangeStatus;

/**
 *
 * @author sergi
 */
@Service
public class ChangeStatusService implements IChangeStatus{
    
    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Override
    public Boolean endCare(Entero DogDayCareInoviceId) {
        System.out.println("El id de la petición es: "+ DogDayCareInoviceId.getEntero());    
        Optional<DogDaycareInvoice> daycareInvoice=dogDaycareInvoiceRepository.findById(DogDayCareInoviceId.getEntero());
        //Vamos a cambiar el estado a terminado
        daycareInvoice.get().setDog_daycare_invoice_status("Terminado");
        dogDaycareInvoiceRepository.save(daycareInvoice.get());
        if(daycareInvoice!=null) return true;
        return false;
        
    }
    
}
