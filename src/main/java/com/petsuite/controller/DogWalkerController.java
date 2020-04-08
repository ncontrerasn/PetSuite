package com.petsuite.controller;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.basics.Entero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/dog_walkers")
public class DogWalkerController {

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/all")
    public List<DogWalker> getAllWalkers(){
        return dogWalkerRepository.findAll();
    }

    @PostMapping(value = "/load")
    public DogWalker_Dto createWalker(@Valid @RequestBody DogWalker_Dto dogWalker){
        
           
            
        String sqlB = "SELECT count(*) as users FROM info_user where user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{dogWalker.getUser()}, (rs, rowNum) -> new Entero(
                rs.getInt("users")
        ));
        //String sqlA = "SELECT * FROM info_user where user = ?";
        if(ul2.get(0).getEntero()==0){
           DogWalker realDogWalker= new DogWalker(dogWalker.getDog_walker_name(), dogWalker.getDog_walker_phone(), dogWalker.getDog_walker_e_mail(), dogWalker.getDog_walker_score(), null);
           realDogWalker.setUser(dogWalker.getUser());
           realDogWalker.setPassword(dogWalker.getPassword());
           realDogWalker.setType(2);
            dogWalkerRepository.save(realDogWalker);
       
            
         return dogWalker;
             
        }
   return null;
    }
       
    
    @RequestMapping(value="/login")
    @ResponseBody
    public boolean walkerLogin(@Valid @RequestBody DogWalker dogWalker){
        
     /*   String sqlA = "SELECT * FROM dog_walker where dog_walker_user = ?";
        String dog_walker_user = dogWalker.getDog_walker_user();
        String dog_walker_password = dogWalker.getDog_walker_password();
        List<DogWalker> ul= jdbcTemplate.query(sqlA, new Object[]{dog_walker_user}, (rs, rowNum) -> new DogWalker(
                        rs.getString("dog_walker_user"),
                        rs.getString("dog_walker_password"),
                        rs.getString("dog_walker_name"),
                        rs.getString("dog_walker_phone"),
                        rs.getString("dog_walker_e_mail"),
                        rs.getFloat("dog_walker_score"),
                        null
                ));
        DogWalker u;
        if (ul.isEmpty()==false){
            u = ul.get(0);
            if (u.getDog_walker_password().equals(dog_walker_password)){
                return true;
            }
        }*/
        return false;
    }
}