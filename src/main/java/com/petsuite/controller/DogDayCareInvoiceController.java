package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.services.*;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Entero;
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
@RequestMapping("/api/dog_day_care_invoices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDayCareInvoiceController {

    @Autowired
    QualifyService dogDayCareQualificationService;

    @Autowired
    ChangeStatusService endCareService;

    @Autowired
    GetAllData getAllData;

    @Autowired
    CreateInvoiceService createInvoiceService;

    @Autowired
    ProposePrice proposePrice;
  
    @GetMapping("/all")
    public List<DogDaycareInvoice> getAllInvoices() { return getAllData.getAllInvoices(); }

    @PostMapping("/endService")//Vamos a terminar el servicio del cuidado
    public Boolean endCareSerice(@Valid @RequestBody Entero idDogDayCareInovice)
    {
        //Llamamos al servicio
        return endCareService.endCare(idDogDayCareInovice);
    }
    
    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCareInvoice_Dto createDogDayCareInvoice(@Valid @RequestBody DogDayCareInvoice_Dto dogDaycareInovice) { return createInvoiceService.createDogDayCareInvoice(dogDaycareInovice); }

    @PostMapping("/consultPrice")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCareInvoice_Dto requestPriceDogDayCareInvoice(@Valid @RequestBody DogDayCareInvoice_Dto dogDaycareInovice) { return proposePrice.requestPriceDogDayCareInvoice(dogDaycareInovice); }

    @PostMapping("/score")
    public Cadena scoreDogDayCare(@Valid @RequestBody DogDayCareInvoice_Dto dogDayCareInvoice_dto){ return dogDayCareQualificationService.qualifyDogDayCare(dogDayCareInvoice_dto); }

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