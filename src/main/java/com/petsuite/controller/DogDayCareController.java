/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.basics.Entero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 *
 * @author huber
 */
@RestController
@RequestMapping("/api/dogdaycares")
public class DogDayCareController {

    @Autowired
    DogDaycareRepository dogDaycareRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<DogDaycare> getAllDogDaycares() {
        return dogDaycareRepository.findAll();
    }
    
    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCare_Dto createDogDaycare(@Valid @RequestBody DogDayCare_Dto dogDaycare) {
        
        String sqlB = "SELECT count(*) as users FROM info_user where user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{dogDaycare.getUser()}, (rs, rowNum) -> new Entero(
                rs.getInt("users")
        ));
        //String sqlA = "SELECT * FROM info_user where user = ?";
        if(ul2.get(0).getEntero()==0){
            DogDaycare realDogDayCare= new DogDaycare(dogDaycare.getDog_daycare_e_mail(), dogDaycare.getDog_daycare_address(),dogDaycare.getDog_daycare_name(), dogDaycare.getDog_daycare_type() ,dogDaycare.getDog_daycare_phone(), dogDaycare.getDog_daycare_score(), null, null);
            realDogDayCare.setUser(dogDaycare.getUser());
            realDogDayCare.setPassword(dogDaycare.getPassword());
            realDogDayCare.setType(3);
        
            
         dogDaycareRepository.save(realDogDayCare);
         return dogDaycare;
        }
   return null;
    }
    
    @RequestMapping(value="/login")
    @ResponseBody
    public boolean dogDaycareLogin(@Valid @RequestBody DogDaycare dogDaycare){
     
        return false;
    }
}