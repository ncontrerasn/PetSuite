package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Service_Dto;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.ClientRepository;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dogdaycareservices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDaycareServiceController {

    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    InfoUserRepository infoUserRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<Client> getAllClients() {
       
        return clientRepository.findAll();
    }
    
    @GetMapping("/myServices")
    public List<DogDaycareService> getMyServices(@RequestParam(value = "user") String user) {
        System.out.println("Quiero verificar mis servicios de "+ user);
       
        return dogDaycareServiceRepository.findMyServicesByUser(user);
    }

    
    @PostMapping("/load")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public DogDayCare_Service_Dto createService(@Valid @RequestBody DogDayCare_Service_Dto care_Service_Dto) {

        System.out.println("Diego entro a crear un servicio");
        DogDaycareService  daycareService= new DogDaycareService(null, care_Service_Dto.getDogdaycare_Service_Name(), care_Service_Dto.getDogdaycare_Service_Description(), care_Service_Dto.getDogdaycare_Service_Price(), care_Service_Dto.getDogdaycare_Service_ClientId(), null,null);
        
        
        
            if(daycareService!=null){
                dogDaycareServiceRepository.save(daycareService);
                return care_Service_Dto;
                
            }
        
        return null;
    }

    @PostMapping("/dogList")
    public List<Dog> myDogList(@Valid @RequestBody String user){
        return dogRepository.findByUser(user);
    }

   

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

