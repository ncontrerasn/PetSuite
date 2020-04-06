/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.controller;

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
public class DogDaycareController {

    @Autowired
    DogDaycareRepository dogDaycareRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<DogDaycare> getAllDogDaycares() {
        return dogDaycareRepository.findAll();
    }
    
    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDaycare createDogDaycare(@Valid @RequestBody DogDaycare dogDaycare) {
        String sqlB = "SELECT count(*) as walkers FROM dog_walker where dog_walker_user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{dogDaycare.getDog_daycare_user()}, (rs, rowNum) -> new Entero(
                rs.getInt("walkers")
        ));
        sqlB = "SELECT count(*) as clients FROM client where client_user = ?";
        List<Entero> ul3= jdbcTemplate.query(sqlB, new Object[]{dogDaycare.getDog_daycare_user()}, (rs, rowNum) -> new Entero(
                rs.getInt("clients")
        ));
        sqlB = "SELECT count(*) as daycares FROM dog_daycare where dog_daycare_user = ?";
        List<Entero> ul4= jdbcTemplate.query(sqlB, new Object[]{dogDaycare.getDog_daycare_user()}, (rs, rowNum) -> new Entero(
                rs.getInt("daycares")
        ));
        if(ul2.get(0).getEntero()==0 && ul3.get(0).getEntero()==0 && ul4.get(0).getEntero()==0){
        
        String sqlA = "SELECT * FROM dog_daycare where dog_daycare_user = ?";
        List<DogDaycare> ul= jdbcTemplate.query(sqlA, new Object[]{dogDaycare.getDog_daycare_user()}, (rs, rowNum) -> new DogDaycare(
                        rs.getString("dog_daycare_user"),
                        rs.getString("dog_daycare_name"),
                        rs.getString("dog_daycare_e_mail"),
                        rs.getString("dog_daycare_password"),
                        rs.getString("dog_daycare_address"),
                        rs.getBoolean("dog_daycare_type"),
                        rs.getInt("dog_daycare_phone"),
                        rs.getFloat("dog_daycare_score"),
                        null,
                        null
                ));
        return dogDaycareRepository.save(dogDaycare);
        }
        return new DogDaycare();
    }
    
    @RequestMapping(value="/login")
    @ResponseBody
    public boolean dogDaycareLogin(@Valid @RequestBody DogDaycare dogDaycare){
        
        String sqlA = "SELECT * FROM dog_daycare where dog_daycare_user = ?";
        String dog_daycare_user = dogDaycare.getDog_daycare_user();
        String dog_daycare_password = dogDaycare.getDog_daycare_password();
        List<DogDaycare> ul= jdbcTemplate.query(sqlA, new Object[]{dog_daycare_user}, (rs, rowNum) -> new DogDaycare(
                        rs.getString("dog_daycare_user"),
                        rs.getString("dog_daycare_name"),
                        rs.getString("dog_daycare_e_mail"),
                        rs.getString("dog_daycare_password"),
                        rs.getString("dog_daycare_address"),
                        rs.getBoolean("dog_daycare_type"),
                        rs.getInt("dog_daycare_phone"),
                        rs.getFloat("dog_daycare_score"),
                        null,
                        null
                ));
        DogDaycare u;
        if (ul.isEmpty()==false){
            u = ul.get(0);
            if (u.getDog_daycare_password().equals(dog_daycare_password)){
                return true;
            }
        }
        return false;
    }
}
