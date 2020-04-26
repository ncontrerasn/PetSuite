package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    @PostMapping("/register")
    public Dog_Dto createDog(@Valid @RequestBody Dog_Dto dog_dto){
        Dog dog = new Dog();
        Dog_Dto dogRes = new Dog_Dto();

        dog.setClient_id(dog_dto.getClient_id());
        dog.setDog_notes(dog_dto.getDog_notes());
        dog.setDog_name(dog_dto.getDog_name());
        dog.setDog_age(dog_dto.getDog_age());
        dog.setDog_height(dog_dto.getDog_height());
        dog.setDog_race(dog_dto.getDog_race());
        dog.setDog_weight(dog_dto.getDog_weight());

        dog = dogRepository.save(dog);

        dogRes.setClient_id(dog.getClient_id());
        dogRes.setDog_notes(dog.getDog_notes());
        dogRes.setDog_name(dog.getDog_name());
        dogRes.setDog_age(dog.getDog_age());
        dogRes.setDog_height(dog.getDog_height());
        dogRes.setDog_race(dog.getDog_race());
        dogRes.setDog_weight(dog.getDog_weight());

        return dogRes;
    }

    @PostMapping("/dogsByDogWalkerId")
    public List<Dog_Dto> createDog(@Valid @RequestBody String dogWalkerName){

        return null;
    }

}
