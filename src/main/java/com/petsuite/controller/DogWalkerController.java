package com.petsuite.controller;

import com.petsuite.Services.model.DogWalker;
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
    public List<DogWalker> getAll(){
        return dogWalkerRepository.findAll();
    }

    @PostMapping(value = "/load")
    public DogWalker persist(@Valid @RequestBody final DogWalker dogWalker){
        String sqlB = "SELECT count(*) as walkers FROM dog_walker where dog_walker_user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{dogWalker.getDog_walker_user()}, (rs, rowNum) -> new Entero(
                rs.getInt("walkers")
        ));
        if(ul2.get(0).getEntero()==0){

            String sqlA = "SELECT * FROM dog_walker where dog_walker_user = ?";
            List<DogWalker> ul= jdbcTemplate.query(sqlA, new Object[]{dogWalker.getDog_walker_user()}, (rs, rowNum) -> new DogWalker(
                    rs.getString("dog_walker_e_mail"),
                    rs.getString("dog_walker_password"),
                    rs.getString("dog_walker_name"),
                    rs.getString("dog_walker_phone"),
                    rs.getString("dog_walker_e_mail"),
                    rs.getFloat("dog_walker_score"),
                    null
            ));
            return dogWalkerRepository.save(dogWalker);
        }
        return new DogWalker();
    }
}
