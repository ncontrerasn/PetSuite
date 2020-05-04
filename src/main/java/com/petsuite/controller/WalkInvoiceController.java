package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.basics.Cadena;
import java.time.LocalDateTime;

import com.petsuite.basics.CadenaDoble;
import com.petsuite.basics.Entero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/walkinvoices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class WalkInvoiceController {

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    DogRepository dogRepository;

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
        }else
            return null;
    }

    @PostMapping("/score")//para pedir otro paseo debe estar calificado al paseador. Pero para comenzar un paseo, se hace un recibo con status 0, cuando se califica al paseador, se pone status 1, cuando se califica ya es porque se ha terminado el paseo
    public Cadena scoreDogWalker(@Valid @RequestBody WalkInvoice_Dto walkInvoice_dto){//se podria hacer un dto solo con id factura, id paseador y puntaje
        System.out.println("Diego va a calificar con id : "+ walkInvoice_dto.getWalk_invoice_id()+ " y con score : "+ walkInvoice_dto.getWalker_score());  
        int updatedInvoice = walkInvoiceRepository.scoreWalker(walkInvoice_dto.getWalker_score(), walkInvoice_dto.getWalk_invoice_id());
        if(updatedInvoice == 1){
            float score = walkInvoiceRepository.scoreAvg(walkInvoice_dto.getDog_walker_id());
            int updatedWalkerScore = dogWalkerRepository.updateScore(score, walkInvoice_dto.getDog_walker_id());
            if(updatedWalkerScore == 1)
                return new Cadena("Paseador calificado correctamente");
        }
        return new Cadena("Error calificando al paseador");
    }

    @PostMapping("/invoicesByStatus")
    public List<WalkInvoice> invoicesByStatus(@Valid @RequestBody Cadena cadena){
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByStatus(cadena.getCadena());
        return walkInvoices;
    }

    @PostMapping("/invoicesByWalker")//devuleve todos los recibos sin importar el estado
    public List<WalkInvoice> invoicesByWalker(@Valid @RequestBody Cadena cadena){
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalker(cadena.getCadena());
        return walkInvoices;
    }

    @PostMapping("/invoicesByWalkerAndStatus")//devuleve todos los recibos de paseos con un estado especifico. Se podria unir con el de arriba
    public List<WalkInvoice> findByWalkerAndStatus(@Valid @RequestBody CadenaDoble cadenaDoble){
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadenaDoble.getCadena1(), cadenaDoble.getCadena2());
        return walkInvoices;
    }
    
    @PostMapping("/invoicesAccepted")
    public List<WalkInvoice_Dto> findByStatusAccepted(@Valid @RequestBody Cadena cadena){
        System.out.println("Diego necesita saber si entraste a lo acpetado");
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadena.getCadena(), "Aceptar");
        List<WalkInvoice_Dto> listaReal= new ArrayList<>();
        for (int i = 0; i < walkInvoices.size(); i++) {
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
    
    
    @PostMapping("/invoicesProgress")
    public List<WalkInvoice_Dto> findByStatusProgress(@Valid @RequestBody Cadena cadena){
        System.out.println("Diego esta solicitando los del progreso");
       List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadena.getCadena(), "En progreso");
        List<WalkInvoice_Dto> listaReal= new ArrayList<>();
        for (int i = 0; i < walkInvoices.size(); i++) {
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
    
        @PostMapping("/invoicesEndedWalkers")
    public List<WalkInvoice> findByStatusEndedWalker(@Valid @RequestBody Cadena cadena){
        System.out.println("Diego esta solicitando los que terminaron ahora si");
        List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByWalkerAndStatus(cadena.getCadena(), "Terminado");
        return walkInvoices;
    }
    
    @PostMapping("/invoicesEndedClient")
    public List<WalkInvoice_Dto> findByStatusEndedClient(@Valid @RequestBody Cadena cadena){
        System.out.println("Diego esta solicitando los que terminaron ahora si, pero con cliente");
        System.out.println("Diego esta solicitando los del progreso");
       List<WalkInvoice> walkInvoices = walkInvoiceRepository.findByUserAndStatus(cadena.getCadena(), "Terminado");
        List<WalkInvoice_Dto> listaReal= new ArrayList<>();
        for (int i = 0; i < walkInvoices.size(); i++) {
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
    
    
    

    @PostMapping("/dogsByWalkerAndStatusProgress")
    public List<Dog> findDogsByWalkerAndStatusAccepted(@Valid @RequestBody Cadena cadena){
        System.out.println("Diego esta buscando todos los perros en progreso");
        List<Dog> dogs = new ArrayList<>();
        List<Integer> accepted = walkInvoiceRepository.findByWalkerAndStatusAccepted(cadena.getCadena(), "En progreso");
        for(int i = 0; i < accepted.size(); i++)
            dogs.add(dogRepository.findByDogId(accepted.get(i)));
        return dogs;
    }

    @PostMapping("/updateInvoiceStatus")
    public List<WalkInvoice> updateInvoiceStatus(@Valid @RequestBody Entero entero) throws InterruptedException{
        Optional<WalkInvoice> walkInvoice = walkInvoiceRepository.findById(entero.getEntero());
        String status = walkInvoice.get().getWalk_invoice_status();
        
        
        
        
        switch(status){
            case "Aceptar":
                
                
               walkInvoice.get().setWalk_invoice_status("En progreso");
               walkInvoiceRepository.save(walkInvoice.get());
                
                if(walkInvoice.get().getWalk_invoice_status() == "En progreso")
                    
                    return walkInvoiceRepository.findByWalkerAcceptedProgress(walkInvoice.get().getDog_walker_id());
                break;
            case "En progreso":
               walkInvoice.get().setWalk_invoice_status("Terminado");
               walkInvoiceRepository.save(walkInvoice.get());
               
                if(walkInvoice.get().getWalk_invoice_status() == "Terminado")
                    return walkInvoiceRepository.findByWalkerAcceptedProgress(walkInvoice.get().getDog_walker_id());
                break;
        }
        return null; 
    }

}
