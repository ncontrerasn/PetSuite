package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.basics.Cadena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogController {

    @Autowired
    DogRepository dogRepository;

    @GetMapping("/all")
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    @PostMapping("/register")
    public Dog_Dto createDog(@Valid @RequestBody Dog_Dto dog){
        Dog dogReal=new Dog(dog.getDog_name(), dog.getDog_race(), dog.getDog_height(), dog.getDog_weight(), dog.getDog_age(), dog.getDog_notes(), dog.getClient_id());

        dogReal = dogRepository.save(dogReal);

        if(dogReal!=null)
            return dog;
        else
            return null;
    }

    @PostMapping("/findmydog")
    public List<Dog> finDogsById(@Valid @RequestBody Cadena user){
        System.out.println("El usuario que me llego es: "+ user);
        return dogRepository.findByUser(user.getCadena());
    }

}

