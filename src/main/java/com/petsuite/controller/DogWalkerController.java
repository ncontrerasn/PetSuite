package com.petsuite.controller;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.basics.Cadena;
import com.petsuite.basics.Flotante;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/dog_walkers")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogWalkerController {

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    InfoUserRepository infoUserRepository;

    @Autowired
    DogRepository dogRepository;

    @GetMapping(value = "/all")
    public List<DogWalker> getAllClients() {
       
        
        return dogWalkerRepository.findAll();
    }
    @PostMapping(value = "/load")
    public DogWalker_Dto createWalker(@Valid @RequestBody DogWalker_Dto dogWalker){

        if(!infoUserRepository.existsById(dogWalker.getUser())){
            DogWalker realDogWalker= new DogWalker(dogWalker.getDog_walker_score(), null);
            realDogWalker.setUser(dogWalker.getUser());
            realDogWalker.setPassword(dogWalker.getPassword());
            realDogWalker.setRole("ROLE_DOGWALKER");
            realDogWalker.setName(dogWalker.getDog_walker_name());
            realDogWalker.setDog_walker_score((float)3.0);
            realDogWalker.setPhone(dogWalker.getDog_walker_phone());
            realDogWalker.setE_mail(dogWalker.getDog_walker_e_mail());
            dogWalkerRepository.save(realDogWalker);

            return dogWalker;
        }
    return null;
    }

    @PostMapping(value = "/PendingDogList")
    public List<Optional<Dog>> PendingDogList(@Valid @RequestBody Cadena dogWalker){

        List<Optional<Dog>> dogs = new ArrayList<>();
        List<Integer> dogs_ids = walkInvoiceRepository.findByDog_walker_id_and_status_true(dogWalker.getCadena());
        for(int i = 0; i < dogs_ids.size(); i++)
            dogs.add(dogRepository.findById(dogs_ids.get(i)));
        return dogs;
    }

    @PostMapping(value = "/CompletedDogList")
    public List<Optional<Dog>> CompletedDogList(@Valid @RequestBody String dogWalker){

        List<Optional<Dog>> dogs = new ArrayList<>();
        List<Integer> dogs_ids = walkInvoiceRepository.findByDog_walker_id_and_status_false(dogWalker);
        for(int i = 0; i < dogs_ids.size() - 1; i++)
            dogs.add(dogRepository.findById(dogs_ids.get(i)));
        return dogs;

    }
    
    @PostMapping(value = "/getCalification")
    public Flotante getQualifications(@Valid @RequestBody Cadena cadena){
        System.out.println("Me esta llengando el usuario para flotante: "+ cadena.getCadena());
        Optional<DogWalker> dogWalker= dogWalkerRepository.findById(cadena.getCadena());
        
        return new Flotante(dogWalker.get().getDog_walker_score());

    }

    @PostMapping(value = "/dogList")
    public List<Dog> dogList(@Valid @RequestBody Cadena dogWalker){

        List<Dog> dogs = new ArrayList<>();
        List<Integer> dogs_ids = walkInvoiceRepository.findByDog_walker_id(dogWalker.getCadena());
        System.out.println("Tamanio lista es: "+ dogs_ids.size());
        
       System.out.println("probando el repo: "+ dogRepository.findByDogId(1).getDog_name());
       for(int i = 0; i < dogs_ids.size() ; i++){
          dogs.add(dogRepository.findByDogId(dogs_ids.get(i)));
       }
       
      /*    
        System.out.println(dogs);*/
      
        return dogs;
    }

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