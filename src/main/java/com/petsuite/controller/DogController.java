package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
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
    public Dog createDog(@Valid @RequestBody Dog dog){
        dog = dogRepository.save(dog);
        return dog;
    }

}
