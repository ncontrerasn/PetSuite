package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;
import com.petsuite.Services.basics.Flotante;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.services.interfaces.IShowWalkInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowWalkInvoiceService implements IShowWalkInvoice {

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    DogRepository dogRepository;
    
    @Autowired
    InfoUserRepository infoUserRepository;

    @Override
    public List<Optional<Dog>> PendingDogList(Cadena dogWalker)
    {
        List<Optional<Dog>> dogs = new ArrayList<>();
        List<Integer> dogs_ids = walkInvoiceRepository.findByDog_walker_id_and_status_true(dogWalker.getCadena());
        for(int i = 0; i < dogs_ids.size(); i++)
            dogs.add(dogRepository.findById(dogs_ids.get(i)));
        return dogs;
    }

    @Override
    public List<Optional<Dog>> CompletedDogList(Cadena dogWalker)
    {
        List<Optional<Dog>> dogs = new ArrayList<>();
        List<Integer> dogs_ids = walkInvoiceRepository.findByDog_walker_id_and_status_false(dogWalker.getCadena());
        for(int i = 0; i < dogs_ids.size() - 1; i++)
            dogs.add(dogRepository.findById(dogs_ids.get(i)));
        return dogs;
    }

    @Override
    public List<WalkInvoice> invoicesByStatus(Cadena cadena)
    {
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByStatus(cadena.getCadena());
        return walkInvoices;
    }

    @Override
    public List<WalkInvoice> invoicesByWalker(Cadena cadena)
    {
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalker(cadena.getCadena());
        return walkInvoices;
    }

    @Override
    public List<WalkInvoice> findByWalkerAndStatus(CadenaDoble cadenaDoble)
    {
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadenaDoble.getCadena1(), cadenaDoble.getCadena2());
        return walkInvoices;
    }

    @Override
    public List<WalkInvoice_Dto> findByStatusAccepted(Cadena cadena)
    {
        String roleUserWhoCancel =infoUserRepository.findRoleBySuer(cadena.getCadena());
        List<WalkInvoice> walkInvoices= new ArrayList<>();
        
        if(roleUserWhoCancel.equals("ROLE_CLIENT"))
        {
        walkInvoices = walkInvoiceRepository.findByClientAndStatus(cadena.getCadena(), "Aceptar");
            
       
        }else if(roleUserWhoCancel.equals("ROLE_DOGWALKER"))
        {
        walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadena.getCadena(), "Aceptar");
                  
       

        }
        
        
        List<WalkInvoice_Dto> listaReal= new ArrayList<>();
        for (int i = 0; i < walkInvoices.size(); i++)
        {
            Dog dog = dogRepository.findByDogId(walkInvoices.get(i).getDog_id());
            WalkInvoice_Dto invoice_Dto=new WalkInvoice_Dto(walkInvoices.get(i).getWalk_invoice_id(), walkInvoices.get(i).getWalk_invoice_price(),walkInvoices.get(i).getWalk_invoice_status(),walkInvoices.get(i).getClient_id(), walkInvoices.get(i).getDog_walker_id(),walkInvoices.get(i).getWalk_invoice_notes(), walkInvoices.get(i).getDog_id(), walkInvoices.get(i).getWalker_score(),walkInvoices.get(i).getWalk_invoice_date(),walkInvoices.get(i).getWalk_invoice_address(),walkInvoices.get(i).getWalk_invoice_duration(),null,null,0,0);
            invoice_Dto.setDog_name(dog.getDog_name());
            invoice_Dto.setDog_height(dog.getDog_height());
            invoice_Dto.setDog_race(dog.getDog_race());
            invoice_Dto.setDog_weight(dog.getDog_weight());
            listaReal.add(invoice_Dto);
        }
        return listaReal;
    }

    @Override
    public List<WalkInvoice_Dto> findByStatusProgress(Cadena cadena)
    {
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadena.getCadena(), "En progreso");
        List<WalkInvoice_Dto> listaReal= new ArrayList<>();
        for (int i = 0; i < walkInvoices.size(); i++)
        {
            Dog dog = dogRepository.findByDogId(walkInvoices.get(i).getDog_id());
            WalkInvoice_Dto invoice_Dto=new WalkInvoice_Dto(walkInvoices.get(i).getWalk_invoice_id(), walkInvoices.get(i).getWalk_invoice_price(),walkInvoices.get(i).getWalk_invoice_status(),walkInvoices.get(i).getClient_id(), walkInvoices.get(i).getDog_walker_id(),walkInvoices.get(i).getWalk_invoice_notes(), walkInvoices.get(i).getDog_id(), walkInvoices.get(i).getWalker_score(),walkInvoices.get(i).getWalk_invoice_date(),walkInvoices.get(i).getWalk_invoice_address(),walkInvoices.get(i).getWalk_invoice_duration(),null,null,0,0);
            invoice_Dto.setDog_name(dog.getDog_name());
            invoice_Dto.setDog_height(dog.getDog_height());
            invoice_Dto.setDog_race(dog.getDog_race());
            invoice_Dto.setDog_weight(dog.getDog_weight());
            listaReal.add(invoice_Dto);
        }
        return listaReal;
    }

    @Override
    public List<WalkInvoice> findByStatusEndedWalker(Cadena cadena)
    {
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadena.getCadena(), "Terminado");
        return walkInvoices;
    }

    @Override
    public List<WalkInvoice_Dto> findByStatusEndedClient(Cadena cadena)
    {
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByUserAndStatus(cadena.getCadena(), "Terminado");
        List<WalkInvoice_Dto> listaReal= new ArrayList<>();
        for (int i = 0; i < walkInvoices.size(); i++)
        {
            Dog dog = dogRepository.findByDogId(walkInvoices.get(i).getDog_id());
            WalkInvoice_Dto invoice_Dto=new WalkInvoice_Dto(walkInvoices.get(i).getWalk_invoice_id(), walkInvoices.get(i).getWalk_invoice_price(),walkInvoices.get(i).getWalk_invoice_status(),walkInvoices.get(i).getClient_id(), walkInvoices.get(i).getDog_walker_id(),walkInvoices.get(i).getWalk_invoice_notes(), walkInvoices.get(i).getDog_id(), walkInvoices.get(i).getWalker_score(),walkInvoices.get(i).getWalk_invoice_date(),walkInvoices.get(i).getWalk_invoice_address(),walkInvoices.get(i).getWalk_invoice_duration(),null,null,0,0);
            invoice_Dto.setDog_name(dog.getDog_name());
            invoice_Dto.setDog_height(dog.getDog_height());
            invoice_Dto.setDog_race(dog.getDog_race());
            invoice_Dto.setDog_weight(dog.getDog_weight());
            //para que solo le muestre al cliente solo las que no tienen calificación, pues las que ya tienen, ya nos las puede volver a calificar
            if(walkInvoices.get(i).getWalker_score()==null)
                listaReal.add(invoice_Dto);
        }
        return listaReal;
    }

}
