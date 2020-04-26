package com.petsuite.controller;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.basics.Entero;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
@RequestMapping(value = "/api/dog_walkers")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogWalkerController {

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/all")
    public List<DogWalker> getAllWalkers(){
        return dogWalkerRepository.findAll();
    }

    @PostMapping(value = "/load")
    public DogWalker_Dto createWalker(@Valid @RequestBody DogWalker_Dto dogWalker){
            
        String sqlB = "SELECT count(*) as users FROM info_user where user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{dogWalker.getUser()}, (rs, rowNum) -> new Entero(
                rs.getInt("users")
        ));
        //String sqlA = "SELECT * FROM info_user where user = ?";
        if(ul2.get(0).getEntero()==0){
           DogWalker realDogWalker= new DogWalker(dogWalker.getDog_walker_name(), dogWalker.getDog_walker_phone(), dogWalker.getDog_walker_e_mail(), dogWalker.getDog_walker_score(), null);
           realDogWalker.setUser(dogWalker.getUser());
           realDogWalker.setPassword(dogWalker.getPassword());
           realDogWalker.setRole("ROLE_DOGWALKER");
           dogWalkerRepository.save(realDogWalker);

         return dogWalker;
        }
    return null;
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

    @RequestMapping(value="/login")
    @ResponseBody
    public boolean walkerLogin(@Valid @RequestBody DogWalker dogWalker){
        
     /*   String sqlA = "SELECT * FROM dog_walker where dog_walker_user = ?";
        String dog_walker_user = dogWalker.getDog_walker_user();
        String dog_walker_password = dogWalker.getDog_walker_password();
        List<DogWalker> ul= jdbcTemplate.query(sqlA, new Object[]{dog_walker_user}, (rs, rowNum) -> new DogWalker(
                        rs.getString("dog_walker_user"),
                        rs.getString("dog_walker_password"),
                        rs.getString("dog_walker_name"),
                        rs.getString("dog_walker_phone"),
                        rs.getString("dog_walker_e_mail"),
                        rs.getFloat("dog_walker_score"),
                        null
                ));
        DogWalker u;
        if (ul.isEmpty()==false){
            u = ul.get(0);
            if (u.getDog_walker_password().equals(dog_walker_password)){
                return true;
            }
        }*/
        return false;
    }


}