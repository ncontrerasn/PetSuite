package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;
import com.petsuite.Services.basics.Flotante;
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
        return dogs;
    }

    //esto ya esta en otro lado
//    @PostMapping(value = "/updatescore")
//    public Float updateScore(@Valid @RequestBody CadenaDoble cadena){
//
//        Optional<DogWalker> dogWalker= dogWalkerRepository.findById(cadena.getCadena1());
//
//        Float score =  dogWalker.get().getDog_walker_score();
//
//        score = (score + Float.parseFloat(cadena.getCadena2()))/2;
//
//        int Worked = 0;
//
//        Worked = dogWalkerRepository.updateScore(score,cadena.getCadena1());
//
//        if (Worked!=1)
//        {
//            return null;
//        }
//
//        return score;
//
//    }

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
    public DogWalker_Dto updateAll(@Valid @RequestBody DogWalker_Dto user_dto){

        System.out.println(user_dto);

        DogWalker_Dto DogWalk_DTO = user_dto;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(user_dto.getUser());

        if (checkUser!=null)
        {

            uppdateReturns = updateUserPassword(user_dto.getUser(),user_dto.getPassword());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setPassword(null);
            }
            DogWalk_DTO.setPassword(null);
            uppdateReturns = updateName(user_dto.getUser(),user_dto.getDog_walker_name());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setDog_walker_name(null);
            }

            uppdateReturns = updatePhone(user_dto.getUser(),user_dto.getDog_walker_phone());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setDog_walker_phone(null);
            }

            uppdateReturns = updateMail(user_dto.getUser(),user_dto.getDog_walker_e_mail());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setDog_walker_e_mail(null);
            }

        }else{
            DogWalk_DTO = new DogWalker_Dto();
        }
        DogWalk_DTO.setRole(user_dto.getRole());
        DogWalk_DTO.setToken(user_dto.getToken());
        System.out.println(DogWalk_DTO.getDog_walker_e_mail());
        return DogWalk_DTO;

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