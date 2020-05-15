package com.petsuite.controller;

import com.petsuite.Services.compositeKey.Service_Invoice_Id;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.DogDayCareService_DogDayCareInvoice;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.Service_InvoiceRepository;
import com.petsuite.basics.EnteroDoble;
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
@RequestMapping("/api/service_invoices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class Service_invoiceController {

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;
    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;
    
    
    @Autowired
    Service_InvoiceRepository service_InvoiceRepository ;
    
    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


     @GetMapping("/all")
    public List<DogDayCareService_DogDayCareInvoice> getAllClients() {
      return service_InvoiceRepository.findAll();
    }

   
    public DogDayCareService_DogDayCareInvoice createDogDaycareServiceInvoice( EnteroDoble enteroDoble, DogDaycareInvoice dogDaycareInvoice) {
       DogDayCareService_DogDayCareInvoice careInvoice= new DogDayCareService_DogDayCareInvoice();
       
        System.out.println("El entero doble que me llega es: "+ enteroDoble);
       careInvoice.setDogDaycareInvoice(dogDaycareInvoice);
        System.out.println(dogDaycareServiceRepository.findById(1));
       careInvoice.setId(new Service_Invoice_Id(enteroDoble.getEntero1(),enteroDoble.getEntero2()));
       
       if(careInvoice.getDogDaycareInvoice()!=null && careInvoice.getDogDaycareService()!=null){
           
           service_InvoiceRepository.save(careInvoice);
           return careInvoice;
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