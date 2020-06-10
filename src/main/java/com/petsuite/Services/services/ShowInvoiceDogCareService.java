/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.services.interfaces.IShowInvoiceDogCare;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.basics.CadenaDoble;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergi
 */
@Service
public class ShowInvoiceDogCareService implements IShowInvoiceDogCare{

    @Autowired
    InfoUserRepository infoUserRepository;

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Override
    public List<DogDayCareInvoice_Dto> showInovicesByStatus(CadenaDoble cadenaDoble)
    {
        String user = cadenaDoble.getCadena1();
        String status= cadenaDoble.getCadena2();
        //Queremos saber si es cliente o guardería
        String roleUser =infoUserRepository.findRoleBySuer(user);
        List <DogDaycareInvoice> invoices= new ArrayList<>();
        List<DogDayCareInvoice_Dto> dtoInvoices= new ArrayList<>();

        if(roleUser.equals("ROLE_CLIENT"))
        {
        invoices=dogDaycareInvoiceRepository.findInvoicesByClientAndStatus(user, status);

        }else if(roleUser.equals("ROLE_DOGDAYCARE"))
        {
         invoices=dogDaycareInvoiceRepository.findInvoicesByDogDayCareAndStatus(user, status);

        }
        
         for (int i = 0; i <invoices.size(); i++)
         {
             DogDayCareInvoice_Dto newInvoices=new DogDayCareInvoice_Dto(invoices.get(i).getDog_daycare_invoice_id(),invoices.get(i).getDog_daycare_invoice_date().toString(),invoices.get(i).getDog_daycare_invoice_duration(), invoices.get(i).getDog_daycare_invoice_price(), invoices.get(i).getDog_daycare_invoice_status(), invoices.get(i).getDog_daycare_id(), invoices.get(i).getClient_id(), invoices.get(i).getDog_id(), null, invoices.get(i).getDog_daycare_score(),null,null);
             List<String> services= dogDaycareInvoiceRepository.findNameServicesByInvoiceId(invoices.get(i).getDog_daycare_invoice_id());
             newInvoices.setDog_daycare_invoice_dog_name(dogDaycareInvoiceRepository.findDogNameByInvoiceId(invoices.get(i).getDog_daycare_invoice_id()));
             newInvoices.setDog_daycare_invoice_services_names(services);
             if(invoices.get(i).getDog_daycare_score()==null)
             dtoInvoices.add(newInvoices);
         }
        return dtoInvoices;
    }

   

    

}
