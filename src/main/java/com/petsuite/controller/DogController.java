package com.petsuite.controller;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.services.FindDogService;
import com.petsuite.Services.services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogController {

    @Autowired
    DogRepository dogRepository;

    @Autowired
    InfoUserRepository infoUserRepository;

    @Autowired
    FindDogService findDog;

    @Autowired
    UpdateService updateService;

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

    @PostMapping("/update")
    public Dog_Dto updateAll(@Valid @RequestBody Dog_Dto dog){

        return updateService.UpdateDog(dog);

    }

    @PostMapping("/findespecificdog")
    public Dog_Dto findEspecificDog(@Valid @RequestBody Entero Dog_id){
        System.out.println(Dog_id);
        return findDog.find(Dog_id);
    }

}

