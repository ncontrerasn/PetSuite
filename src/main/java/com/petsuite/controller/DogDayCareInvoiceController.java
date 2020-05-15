package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/api/dog_day_care_invoices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDayCareInvoiceController {

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

     @GetMapping("/all")
    public List<DogDaycareInvoice> getAllClients() {
      return dogDaycareInvoiceRepository.findAll();
    }

    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCareInvoice_Dto createDogDaycareInvoice(@Valid @RequestBody DogDayCareInvoice_Dto dogDaycareInovice) {
        //para formatear la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dogDaycareInovice.getDog_daycare_invoice_date(), formatter);
       DogDaycareInvoice daycareInvoice= new DogDaycareInvoice(null, dateTime, dogDaycareInovice.getDog_daycare_invoice_duration(), dogDaycareInovice.getDog_daycare_invoice_price(), dogDaycareInovice.getDog_daycare_invoice_status(), dogDaycareInovice.getDog_daycare_invoice_dogdaycare_id(), dogDaycareInovice.getDog_daycare_invoice_client_id(), dogDaycareInovice.getDog_daycare_invoice_dog_id(), null, null, null, null);
        
        if(daycareInvoice!=null){
            return dogDaycareInovice;
        }
        return null;
      
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