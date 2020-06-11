package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;

import com.petsuite.Services.services.GetAllData;
import com.petsuite.Services.services.RegisterService;
import com.petsuite.Services.services.ShowInvoiceDogCareService;
import com.petsuite.Services.services.UpdateService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dog_day_cares")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDayCareController {

    @Autowired
    RegisterService registerService;

    @Autowired
    UpdateService updateService;

    @Autowired
    GetAllData getAllData;

    @Autowired
    ShowInvoiceDogCareService showInvoiceDogCareService;

    @GetMapping("/all")
    public List<DogDayCare_Dto> getAllDogDayCares() { return  getAllData.getAllDogDayCares(); }

    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCare_Dto createDogDaycare(@Valid @RequestBody DogDayCare_Dto dogDaycare) { return registerService.createDogDaycare(dogDaycare); }

    @PostMapping(value = "/CaresListStatus")
    public List<DogDayCareInvoice_Dto> PendingDogList(@Valid @RequestBody CadenaDoble cadenaDoble){ return showInvoiceDogCareService.showInovicesByStatus(cadenaDoble); }
    
    @PostMapping("/update")
    public DogDayCare_Dto updateAll(@Valid @RequestBody DogDayCare_Dto user_dto){ return updateService.UpdateDayCare(user_dto); }

    private String getJWTToken(String username){
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