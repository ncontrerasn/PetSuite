/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.basics.Cadena;
import com.petsuite.basics.CadenaDoble;
import com.petsuite.basics.Entero;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author huber
 */
@RestController
@RequestMapping("/api/dog_day_cares")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDayCareController {

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    InfoUserRepository infoUserRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

     @GetMapping("/all")
    public List<DogDaycare> getAllClients() {
       
        
        return dogDaycareRepository.findAll();
    }
    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCare_Dto createDogDaycare(@Valid @RequestBody DogDayCare_Dto dogDaycare) {

        if(!infoUserRepository.existsById(dogDaycare.getUser())){
            DogDaycare realDogDayCare= new DogDaycare(dogDaycare.getDog_daycare_address(), dogDaycare.getDog_daycare_type() , dogDaycare.getDog_daycare_score(), null, null);
            realDogDayCare.setUser(dogDaycare.getUser());
            realDogDayCare.setPassword(dogDaycare.getPassword());
            realDogDayCare.setRole("ROLE_DOGDAYCARE");
            realDogDayCare.setName(dogDaycare.getDog_daycare_name());
            realDogDayCare.setDog_daycare_score((float)3.0);
            realDogDayCare.setE_mail(dogDaycare.getDog_daycare_e_mail());
            realDogDayCare.setPhone(dogDaycare.getDog_daycare_phone());
            dogDaycareRepository.save(realDogDayCare);

            return dogDaycare;
        }
   return null;
    }

    public Integer updateType(String user, boolean type){

        int Worked = 0;

        Worked = dogDaycareRepository.updateType(type,user);

        return Worked;

    }

    @PostMapping(value = "/updatescore")
    public Float updateScore(@Valid @RequestBody CadenaDoble cadena){

        Optional<DogDaycare> dogDaycare= dogDaycareRepository.findById(cadena.getCadena1());

        Float score =  dogDaycare.get().getDog_daycare_score();

        score = (score + Float.parseFloat(cadena.getCadena2()))/2;

        int Worked = 0;

        Worked = dogDaycareRepository.updateScore(score,cadena.getCadena1());

        if (Worked!=1)
        {
            return null;
        }

        return score;

    }

    public Integer updateAddress(String user, String address){

        int Worked = 0;

        Worked = dogDaycareRepository.updateAddress(address,user);

        return Worked;

    }

    public Integer updateUserPassword(String user, String password){

        if (password!=null)
        {
            int Worked = 0;

            Worked = infoUserRepository.updateUserPassword(password,user);

            return Worked;
        }

        return 0;

    }

    public Integer updateName(String user, String name){

        int Worked = 0;

        Worked = infoUserRepository.updateClientName(name,user);

        return Worked;

    }

    public Integer updatePhone(String user, String Phone){

        int Worked = 0;

        Worked = infoUserRepository.updateClientPhone(Phone,user);

        return Worked;

    }

    public Integer updateMail(String user, String Mail){

        int Worked = 0;

        Worked = infoUserRepository.updateClientEmail(Mail,user);

        return Worked;

    }

    @PostMapping("/update")
    public DogDayCare_Dto updateAll(@Valid @RequestBody DogDayCare_Dto user_dto){

        System.out.println(user_dto);

        DogDayCare_Dto DayCare_DTO = user_dto;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(user_dto.getUser());

        if (checkUser!=null)
        {

            uppdateReturns = updateUserPassword(user_dto.getUser(),user_dto.getPassword());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setPassword(null);
            }

            uppdateReturns = updateAddress(user_dto.getUser(),user_dto.getDog_daycare_address());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_address(null);
            }

            uppdateReturns = updateName(user_dto.getUser(),user_dto.getDog_daycare_name());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_name(null);
            }

            uppdateReturns = updatePhone(user_dto.getUser(),user_dto.getDog_daycare_phone());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_phone(null);
            }

            uppdateReturns = updateMail(user_dto.getUser(),user_dto.getDog_daycare_e_mail());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_e_mail(null);
            }

            uppdateReturns = updateType(user_dto.getUser(),user_dto.getDog_daycare_type());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_type(null);
            }

        }else{
            DayCare_DTO = new DogDayCare_Dto();
        }

        return DayCare_DTO;

    }

    @PostMapping("/searchbyname")
    public List<DogDayCare_Dto> updateAll(@Valid @RequestBody Cadena name){

        System.out.println(name);

         List<String> usersToReturns = new ArrayList<>();

        List<String> findings = new ArrayList<>();

        String[] Words = name.getCadena().split(" ");

        for (int i=0; i<Words.length; i++){
            System.out.println(Words);
            findings = dogDaycareRepository.searchByName("%"+Words[i]+"%");

            while(!findings.isEmpty()){
                if (!usersToReturns.contains(findings.get(0))) {
                    usersToReturns.add(findings.get(0));
                }
                findings.remove(0);
            }
        }

        List<DogDayCare_Dto> returns = new ArrayList<>();

        DogDayCare_Dto DTO;

        Optional<DogDaycare> DC;

        while(!usersToReturns.isEmpty()){

            DC = dogDaycareRepository.findById(usersToReturns.remove(0));

            System.out.println(DC.get().getUser());

            DTO = new DogDayCare_Dto();

            DTO.setDog_daycare_name(DC.get().getName());
            DTO.setDog_daycare_type(DC.get().getDog_daycare_type());
            DTO.setDog_daycare_score(DC.get().getDog_daycare_score());
            DTO.setDog_daycare_phone(DC.get().getPhone());
            DTO.setDog_daycare_address(DC.get().getDog_daycare_address());
            DTO.setUser(DC.get().getUser());
            DTO.setDog_daycare_e_mail(DC.get().getE_mail());

            returns.add(DTO);

        }

        return returns;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("DOGDAYCARE");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}