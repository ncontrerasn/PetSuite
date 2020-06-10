package com.petsuite.controller;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.DogDayCare_Service_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.services.CreateService;
import com.petsuite.Services.services.FindDogService;
import com.petsuite.Services.services.GetAllData;
import com.petsuite.Services.services.GetServicesService;
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
@RequestMapping("/api/dogdaycareservices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDaycareServiceController {

    @Autowired
    CreateService createService;

    @Autowired
    GetAllData getAllData;

    @Autowired
    GetServicesService getServicesService;

    @Autowired
    FindDogService findDogService;

    @GetMapping("/all")
    public List<DogDaycareService> getAllServices() { return getAllData.getAllServices(); }

    @GetMapping("/myServices")
    public List<DogDaycareService> getMyServices(@RequestParam(value = "user") String user) { return getServicesService.getMyServices(user); }

    @PostMapping("/load")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public DogDayCare_Service_Dto createService(@Valid @RequestBody DogDayCare_Service_Dto care_Service_Dto) { return createService.createService(care_Service_Dto); }

    @PostMapping("/dogList")
    public List<Dog> myDogList(@Valid @RequestBody Cadena user){ return findDogService.myDogList(user); }

    public String getClientJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("CLIENT");

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

        return "Token " + token;
    }

}



