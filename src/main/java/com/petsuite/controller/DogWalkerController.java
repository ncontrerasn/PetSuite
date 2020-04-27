package com.petsuite.controller;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
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
    public List<DogWalker_Dto> getAllClients() {
        List<DogWalker> listapaseadors=dogWalkerRepository.findAll();
        List<DogWalker_Dto> listaClientDto= new ArrayList<DogWalker_Dto>();
        for (int i = 0; i < listapaseadors.size(); i++) {
            DogWalker paseador=listapaseadors.get(i);
            DogWalker_Dto walkerDto=new DogWalker_Dto(paseador.getUser(), paseador.getPassword(),paseador.getName(),paseador.getPhone(), paseador.getE_mail(), paseador.getDog_walker_score(),null,null);
            listaClientDto.add(walkerDto);
             
        }
        return listaClientDto;
    }
    @PostMapping(value = "/load")
    public DogWalker_Dto createWalker(@Valid @RequestBody DogWalker_Dto dogWalker){

        if(!infoUserRepository.existsById(dogWalker.getUser())){
            DogWalker realDogWalker= new DogWalker(dogWalker.getDog_walker_score(), null);
            realDogWalker.setUser(dogWalker.getUser());
            realDogWalker.setPassword(dogWalker.getPassword());
            realDogWalker.setRole("ROLE_DOGWALKER");
            dogWalkerRepository.save(realDogWalker);

            return dogWalker;
        }
    return null;
    }

    @PostMapping(value = "/dogList")
    public List<Optional<Dog>> dogList(@Valid @RequestBody String dogWalker){

        List<Optional<Dog>> dogs = new ArrayList<>();
        List<Integer> dogs_ids = walkInvoiceRepository.findByDog_walker_id(dogWalker);
        for(int i = 0; i < dogs_ids.size() - 1; i++)
            dogs.add(dogRepository.findById(dogs_ids.get(i)));
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