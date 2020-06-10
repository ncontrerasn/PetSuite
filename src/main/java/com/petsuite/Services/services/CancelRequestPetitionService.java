/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services;

import com.petsuite.Services.dto.Cancellation_Dto;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.model.DogDayCareService_DogDayCareInvoice;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.Service_InvoiceRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.services.interfaces.ICancelRequestPetition;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergi
 */



 @Service
public class CancelRequestPetitionService implements ICancelRequestPetition{
    
    @Autowired
    InfoUserRepository infoUserRepository;
    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;
    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;
    @Autowired
    Service_InvoiceRepository service_invoiceRepository;

    @Override
    public Boolean cancelCare(Cancellation_Dto cancellation_Dto) {
        System.out.println("Diego intenta cancelar un cuidado con la razon de : "+ cancellation_Dto.getReasonCancellation());
      String userWhoCancel=cancellation_Dto.getUser_whoCancel();
      String userCancelled=cancellation_Dto.getUser_Cancelled();
      String roleUserWhoCancel =infoUserRepository.findRoleBySuer(userWhoCancel);
       

        if(roleUserWhoCancel.equals("ROLE_CLIENT"))
        {
        //notificar a la guardria que un cliente cancelo el servicio
            
       
        }else if(roleUserWhoCancel.equals("ROLE_DOGDAYCARE"))
        {
        //notificar a la guardria que un cliente cancelo el servicio           
       

        }
        //para borrar la factura hay que hacer un borrado en cascada
        
        List<DogDayCareService_DogDayCareInvoice> myIntermediateList= service_invoiceRepository.findIntermediateServicesIvoiceByInvoiceId(cancellation_Dto.getId_petition());
        for (int i = 0; i < myIntermediateList.size(); i++) {
            System.out.println("Diego borro un untermedio servicio");
            service_invoiceRepository.delete(myIntermediateList.get(i));
        }
        
        //Borramos la factura
         dogDaycareInvoiceRepository.delete(dogDaycareInvoiceRepository.findById(cancellation_Dto.getId_petition()).get());
         //verificamos que se haya borrado bien 
         int numberWithThatid=dogDaycareInvoiceRepository.numberById(cancellation_Dto.getId_petition());
         if(numberWithThatid==0) return true;
         return false;
         
        
        
        
    }

    @Override
    public Boolean cancelWalk(Cancellation_Dto cancellation_Dto) {
        System.out.println("Diego quiere cancelar un paseo con la razón: "+ cancellation_Dto.getReasonCancellation());
      String userWhoCancel=cancellation_Dto.getUser_whoCancel();
      String userCancelled=cancellation_Dto.getUser_Cancelled();
      String roleUserWhoCancel =infoUserRepository.findRoleBySuer(userWhoCancel);
        
        if(roleUserWhoCancel.equals("ROLE_CLIENT"))
        {
        //notificar a la guardria que un cliente cancelo el servicio
            
       
        }else if(roleUserWhoCancel.equals("ROLE_DOGWALKER"))
        {
        //notificar a la guardria que un cliente cancelo el servicio           
       

        }
        //Borramos la factura
         walkInvoiceRepository.delete(walkInvoiceRepository.findById(cancellation_Dto.getId_petition()).get());
         //verificamos que se haya borrado bien 
         int numberWithThatid=walkInvoiceRepository.numberById(cancellation_Dto.getId_petition());
         if(numberWithThatid==0) return true;
         return false;
         
        
        }
    
}
