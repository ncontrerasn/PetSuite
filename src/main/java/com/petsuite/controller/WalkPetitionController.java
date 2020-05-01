package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.basics.Cadena;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/walkpetitions")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class WalkPetitionController {

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @GetMapping("/all")
    public List<WalkPetition> getAllPetitions() {
        return walkPetitionRepository.findAll();
    }

    @PostMapping("/create")
    public WalkPetition_Dto createPeititon(@Valid @RequestBody WalkPetition_Dto walkPetition){
        
        System.out.println("El perro es: "+walkPetition.getDog_id());

//        List<WalkPetition> allWalkPetitionsOfClient = walkPetitionRepository.findPetitionsByUser(walkPetition.getUser());

  //      List<WalkPetition> allWalkPetitionsOfDog = walkPetitionRepository.findPetitionsByDog(walkPetition.getDog_id().toString());


    //    if (allWalkPetitionsOfClient.isEmpty() && allWalkPetitionsOfDog.isEmpty()) {
    
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
        //}
        }

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
    public List<WalkPetition> finPetitionByUser(@Valid @RequestBody String user){
        return walkPetitionRepository.findPetitionsByUser(user);
    }
    
    

}
