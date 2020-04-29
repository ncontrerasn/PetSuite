package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.basics.Cadena;
import java.time.LocalDateTime;
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
    public List<WalkPetition> getAllDogs() {
        return walkPetitionRepository.findAll();
    }

    @PostMapping("/create")
    public WalkPetition_Dto createPeititon(@Valid @RequestBody WalkPetition_Dto walkPetition){
       WalkPetition walkPetitionReal= new WalkPetition(walkPetition.getWalk_petition_id(), walkPetition.getWalk_petition_date_time(), walkPetition.getWalk_petition_address(), walkPetition.getWalk_petition_duration(), walkPetition.getWalk_petition_notes(), walkPetition.getUser(), walkPetition.getDog_id(), null, null);
        
        
        walkPetitionReal = walkPetitionRepository.save(walkPetitionReal);
        
        if(walkPetitionReal!=null)
        return walkPetition;
        else
        return null;
    }
    
    @PostMapping("/findmydog")
    public List<Dog> finDogsById(@Valid @RequestBody Cadena user){
       return null;
      
    }
    
    

}
