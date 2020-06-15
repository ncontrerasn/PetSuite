package com.petsuite.controller;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Flotante;
import com.petsuite.Services.services.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/dog_walkers")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogWalkerController {

    @Autowired
    UpdateService updateService;

    @Autowired
    RegisterService registerService;

    @Autowired
    GetAllDataService getAllData;

    @Autowired
    QualifyService qualifyService;

    @Autowired
    ShowWalkInvoiceService showWalkInvoiceService;

    @Autowired
    FindDogService findDogService;

    @GetMapping(value = "/all")
    public List<DogWalker> getAllWalkers() { return getAllData.getAllWalkers(); }

    @PostMapping(value = "/load")
    public DogWalker_Dto createWalker(@Valid @RequestBody DogWalker_Dto dogWalker){ return registerService.createWalker(dogWalker); }

    @PostMapping(value = "/PendingDogList")
    public List<Optional<Dog>> PendingDogList(@Valid @RequestBody Cadena dogWalker){ return showWalkInvoiceService.PendingDogList(dogWalker); }

    @PostMapping(value = "/CompletedDogList")
    public List<Optional<Dog>> CompletedDogList(@Valid @RequestBody Cadena dogWalker){ return showWalkInvoiceService.CompletedDogList(dogWalker); }
    
    @PostMapping(value = "/getCalification")
    public Flotante getQualifications(@Valid @RequestBody Cadena cadena){ return qualifyService.getQualifications(cadena); }

    @PostMapping(value = "/dogList")
    public List<Dog> walkerDogList(@Valid @RequestBody Cadena dogWalker){ return findDogService.walkerDogList(dogWalker); }

    @PostMapping("/update")
    public DogWalker_Dto updateAll(@Valid @RequestBody DogWalker_Dto user_dto){ return updateService.UpdateDogWalker(user_dto); }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("DOGWALKER");

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