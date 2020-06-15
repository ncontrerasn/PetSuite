package com.petsuite.controller;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.services.FindDogService;
import com.petsuite.Services.services.GetAllDataService;
import com.petsuite.Services.services.RegisterService;
import com.petsuite.Services.services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogController {

    @Autowired
    FindDogService findDog;

    @Autowired
    UpdateService updateService;

    @Autowired
    RegisterService registerService;

    @Autowired
    GetAllDataService getAllData;

    @GetMapping("/all")
    public List<Dog> getAllDogs() { return getAllData.getAllDogs(); }

    @PostMapping("/register")
    public Dog_Dto createDog(@Valid @RequestBody Dog_Dto dog){ return registerService.createDog(dog); }

    @PostMapping("/findmydog")
    public List<Dog> finDogsById(@Valid @RequestBody Cadena user){ return findDog.myDogList(user); }

    @PostMapping("/update")
    public Dog_Dto updateAll(@Valid @RequestBody Dog_Dto dog){ return updateService.UpdateDog(dog); }

    @PostMapping("/findespecificdog")
    public Dog_Dto findEspecificDog(@Valid @RequestBody Entero Dog_id){ return findDog.find(Dog_id); }

}

