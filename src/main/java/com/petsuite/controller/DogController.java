package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.basics.Cadena;
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
        //System.out.println(dogRepository.findByUser(client_id));
        //String usuario= user.getCadena()trim();
        //System.out.println("Mi nombre es: jose"+ " y el nombre que me llega es: "+ usuario);
        return dogRepository.findByUser(user.getCadena());
      
    }

    public Integer updateRace(Integer dog_id, String race){

        int Worked = 0;

        Worked = dogRepository.updateRace(race,dog_id);

        return Worked;

    }

    public Integer updateNotes(Integer dog_id, String notes){

        int Worked = 0;

        Worked = dogRepository.updateNotes(notes,dog_id);

        return Worked;

    }

    public Integer updateName(Integer dog_id, String name){

        int Worked = 0;

        Worked = dogRepository.updateName(name,dog_id);

        return Worked;

    }

    public Integer updateWeight(Integer dog_id, float weight){

        int Worked = 0;

        Worked = dogRepository.updateWeight(weight,dog_id);

        return Worked;

    }

    public Integer updateHeight(Integer dog_id, float height){

        int Worked = 0;

        Worked = dogRepository.updateHeight(height,dog_id);

        return Worked;

    }

    public Integer updateAge(Integer dog_id, Integer age){

        int Worked = 0;

        Worked = dogRepository.updateAge(age,dog_id);

        return Worked;

    }

    @PostMapping("/update")
    public Dog_Dto updateAll(@Valid @RequestBody Dog_Dto dog){

        System.out.println(dog);

        Dog_Dto dogDTO = dog;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(dogDTO.getClient_id());

        if (checkUser!=null)
        {

            uppdateReturns = updateAge(dogDTO.getDog_id(),dogDTO.getDog_age());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_age(null);
            }

            uppdateReturns = updateName(dogDTO.getDog_id(),dogDTO.getDog_name());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_name(null);
            }

            uppdateReturns = updateHeight(dogDTO.getDog_id(),dogDTO.getDog_height());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_height(Float.parseFloat(null));
            }

            uppdateReturns = updateWeight(dogDTO.getDog_id(),dogDTO.getDog_weight());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_weight(Float.parseFloat(null));
            }

            uppdateReturns = updateNotes(dogDTO.getDog_id(),dogDTO.getDog_notes());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_notes(null);
            }

            uppdateReturns = updateRace(dogDTO.getDog_id(),dogDTO.getDog_race());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_race(null);
            }

        }else{
            dogDTO = new Dog_Dto();
        }

        return dogDTO;

    }

}
