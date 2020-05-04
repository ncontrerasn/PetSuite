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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/walkpetitions")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class WalkPetitionController {

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Autowired
    DogRepository dogRepository;
    
    @Autowired
    WalkInvoiceController walkInvoiceController;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;


    @GetMapping("/all")
    public List<WalkPetition_Dto> getAllPetitions() {
        List<WalkPetition> lista= walkPetitionRepository.findAll();
        List<WalkPetition_Dto> listaDtos = new ArrayList<>();
        for(int j = 0; j < lista.size(); j++){
            WalkPetition walkPetition = lista.get(j);
            Optional<Dog> dog = dogRepository.findById(walkPetition.getDog_id());
            if(lista.get(j).getPrice() == null)
                listaDtos.add(new WalkPetition_Dto(walkPetition.getWalk_petition_id(), walkPetition.getWalk_petition_date_time().toString(),
                        walkPetition.getWalk_petition_address(), walkPetition.getWalk_petition_duration(), walkPetition.getWalk_petition_notes(),
                        walkPetition.getUser(), walkPetition.getDog_id(), walkPetition.getPrice(), walkPetition.getWalk_petition_walker_user(),
                        dog.get().getDog_name(), dog.get().getDog_race(), dog.get().getDog_height(), dog.get().getDog_weight(), dog.get().getDog_age(),
                        dog.get().getDog_notes()));
        }
        return listaDtos;
    }

    @PostMapping("/create")
    public WalkPetition_Dto createPeititon(@Valid @RequestBody WalkPetition_Dto walkPetition){
        
        System.out.println("El perro es: "+walkPetition.getDog_id());

//       List<WalkPetition> allWalkPetitionsOfClient = walkPetitionRepository.findPetitionsByUser(walkPetition.getUser());

         List<WalkPetition> allWalkPetitionsOfDog = walkPetitionRepository.findPetitionsByDog(walkPetition.getDog_id().toString());

         List<WalkInvoice> WalkInvoicesAcepted = walkInvoiceRepository.findByStatusAndUserAndDog("Aceptar",walkPetition.getUser(),walkPetition.getDog_id().toString());

        List<WalkInvoice> WalkInvoicesInProgress = walkInvoiceRepository.findByStatusAndUserAndDog("En progreso",walkPetition.getUser(),walkPetition.getDog_id().toString());


         if (allWalkPetitionsOfDog.isEmpty() && WalkInvoicesAcepted.isEmpty() && WalkInvoicesInProgress.isEmpty()) {
    
        System.out.println("La fecha que me llegó es: "+ walkPetition.getWalk_petition_date_time());
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(walkPetition.getWalk_petition_date_time(), formatter);
            
            dateTime.plusHours(-5);

            System.out.println("La fecha que voy a meter es: "+ dateTime);
             
            
            WalkPetition walkPetitionReal = new WalkPetition(dateTime, walkPetition.getWalk_petition_address(), walkPetition.getWalk_petition_duration(), walkPetition.getWalk_petition_notes(), walkPetition.getUser(), walkPetition.getDog_id(), null, null);
            System.out.println("Lo final final es: "+ walkPetitionReal.getWalk_petition_date_time());
            



            walkPetitionReal = walkPetitionRepository.save(walkPetitionReal);

           if (walkPetitionReal != null)
                return walkPetition;
            else
                return null;
        }
        
        return null;
    }

    @DeleteMapping("/abort")
    @ResponseBody
    public void deletePetition(@Valid @RequestBody Integer Petition_id){
        walkPetitionRepository.deletePetition(Petition_id);
    }

    
    @PostMapping("/findbydog")
    public List<WalkPetition> finPetitionByDog(@Valid @RequestBody String dog){
       return walkPetitionRepository.findPetitionsByDog(dog);
    }

    @PostMapping("/findbyuser")
    public List<WalkPetition_Dto> finPetitionByUserWithProposalPrice(@Valid @RequestBody Cadena user){
        System.out.println("El nombre que entra es: , nos vamos con "+user.getCadena() );
        List<WalkPetition> lista= walkPetitionRepository.findPetitionsByUser(user.getCadena());
       
        
        
         List<WalkPetition_Dto> listaDtos = new ArrayList<>();
        for(int j = 0; j < lista.size(); j++){
            if(lista.get(j).getPrice()!=null){
                System.out.println("No es nulo");
            WalkPetition walkPetition = lista.get(j);
            Optional<Dog> dog = dogRepository.findById(walkPetition.getDog_id());

                listaDtos.add(new WalkPetition_Dto(walkPetition.getWalk_petition_id(), walkPetition.getWalk_petition_date_time().toString(),
                        walkPetition.getWalk_petition_address(), walkPetition.getWalk_petition_duration(), walkPetition.getWalk_petition_notes(),
                        walkPetition.getUser(), walkPetition.getDog_id(), walkPetition.getPrice(), walkPetition.getWalk_petition_walker_user(),
                        dog.get().getDog_name(), dog.get().getDog_race(), dog.get().getDog_height(), dog.get().getDog_weight(), dog.get().getDog_age(),
                        dog.get().getDog_notes()));
        }
        }
        return listaDtos;
        
        
    }
     @PostMapping("/propose")
    public  Dog_Dto proposePrice(@Valid @RequestBody WalkPetition_Dto walkPetition_Dto){
         
         WalkPetition petition= walkPetitionRepository.findPetitionsById(walkPetition_Dto.getWalk_petition_id());
         System.out.println("El precio que se propone: "+ walkPetition_Dto.getPrecio_proposal());
         petition.setPrice(walkPetition_Dto.getPrecio_proposal());
         petition.setWalk_petition_walker_user(walkPetition_Dto.getWalk_petition_walker_user());
         
         walkPetitionRepository.save(petition); 
        return null;
    }
    
     @PostMapping("/denyoraccept")
    public  Dog_Dto denyPetition(@Valid @RequestBody WalkInvoice_Dto walkInvoice_Dto){
        
        String status=walkInvoice_Dto.getWalk_invoice_status();
        if(status.equals("Aceptar")){
            walkInvoiceController.createInvoice(walkInvoice_Dto);
            
            
            
        }else{
            WalkPetition petition= walkPetitionRepository.findPetitionsByDogAndByUser(walkInvoice_Dto.getDog_id().toString(),walkInvoice_Dto.getClient_id());
            petition.setWalk_petition_walker_user(null);
            petition.setPrice(null);
            walkPetitionRepository.save(petition);
            
            
            
        }
        
         
        
        return null;
    }
    

}
