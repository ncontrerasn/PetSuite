package com.petsuite.controller;


import com.petsuite.Services.dto.Client_Dto;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycare;

import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;
import com.petsuite.Services.basics.Entero;

import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.basics.Cadena;

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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dog_day_cares")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDayCareController {

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;
    
    @Autowired
    InfoUserRepository infoUserRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

     @GetMapping("/all")
    public List<DogDayCare_Dto> getAllClients() {
         System.out.println("Diego esta solicitando todas las guarderias");
        List<DogDaycare> lista=dogDaycareRepository.findAll();
        List<DogDayCare_Dto> listaEnviar= new ArrayList<>();
         for (int i = 0; i < lista.size(); i++) {
             DogDayCare_Dto  guarderia = new DogDayCare_Dto(lista.get(i).getE_mail(), lista.get(i).getDog_daycare_address(), lista.get(i).getDog_daycare_type(), lista.get(i).getPhone(), lista.get(i).getDog_daycare_score(), lista.get(i).getName(), lista.get(i).getDog_daycare_base_price(), lista.get(i).getDog_daycare_tax());
             listaEnviar.add(guarderia);
               
         }
         return listaEnviar;
    }

    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCare_Dto createDogDaycare(@Valid @RequestBody DogDayCare_Dto dogDaycare) {
        System.out.println("Diego entro");
        if(!infoUserRepository.existsById(dogDaycare.getUser())){
            DogDaycare realDogDayCare= new DogDaycare(dogDaycare.getDog_daycare_address(), dogDaycare.getDog_daycare_type() , dogDaycare.getDog_daycare_score(),dogDaycare.getDog_daycare_price_base(),dogDaycare.getDog_daycare_tax(), null, null);
            realDogDayCare.setUser(dogDaycare.getUser());
            realDogDayCare.setPassword(dogDaycare.getPassword());
            realDogDayCare.setRole("ROLE_DOGDAYCARE");
            realDogDayCare.setName(dogDaycare.getDog_daycare_name());
            realDogDayCare.setDog_daycare_score((float)3.0);
            realDogDayCare.setE_mail(dogDaycare.getDog_daycare_e_mail());
            realDogDayCare.setPhone(dogDaycare.getDog_daycare_phone());
            dogDaycareRepository.save(realDogDayCare);

            return dogDaycare;
        }
   return null;
    }
    
    
     @PostMapping(value = "/pendingCaresList")
    public List<DogDayCareInvoice_Dto> PendingDogList(@Valid @RequestBody Cadena dogDayCare){
        List<DogDayCareInvoice_Dto> dtoInvoices= new ArrayList<>();
       List<DogDaycareInvoice> invoices= dogDaycareInvoiceRepository.findInvoicesByDogDayCare(dogDayCare.getCadena());
         for (int i = 0; i <invoices.size(); i++) {
             DogDayCareInvoice_Dto newInvoices=new DogDayCareInvoice_Dto(invoices.get(i).getDog_daycare_invoice_id(),invoices.get(i).getDog_daycare_invoice_date().toString(),invoices.get(i).getDog_daycare_invoice_duration(), invoices.get(i).getDog_daycare_invoice_price(), invoices.get(i).getDog_daycare_invoice_status(), invoices.get(i).getDog_daycare_id(), invoices.get(i).getClient_id(), invoices.get(i).getDog_id(), null, invoices.get(i).getDog_daycare_score(),null,null);
             List<String> services= dogDaycareInvoiceRepository.findNameServicesByInvoiceId(invoices.get(i).getDog_daycare_invoice_id());
             newInvoices.setDog_daycare_invoice_dog_name(dogDaycareInvoiceRepository.findDogNameByInvoiceId(invoices.get(i).getDog_daycare_invoice_id()));
             newInvoices.setDog_daycare_invoice_services_names(services);
             if(!invoices.get(i).getDog_daycare_invoice_status().equals("Terminado"))
             dtoInvoices.add(newInvoices);
         }
         
         return dtoInvoices;
    }

    public Integer updateType(String user, Boolean type){

        int Worked = 0;

        Worked = dogDaycareRepository.updateType(type,user);

        return Worked;

    }
//Esto ya esta en otro lado
//    @PostMapping(value = "/updatescore")
//    public Float updateScore(@Valid @RequestBody CadenaDoble cadena){
//
//        Optional<DogDaycare> dogDaycare= dogDaycareRepository.findById(cadena.getCadena1());
//
//        Float score =  dogDaycare.get().getDog_daycare_score();
//
//        score = (score + Float.parseFloat(cadena.getCadena2()))/2;
//
//        int Worked = 0;
//
//        Worked = dogDaycareRepository.updateScore(score,cadena.getCadena1());
//
//        if (Worked!=1)
//        {
//            return null;
//        }
//
//        return score;
//
//    }

    public Integer updateAddress(String user, String address){

        int Worked = 0;

        Worked = dogDaycareRepository.updateAddress(address,user);

        return Worked;

    }

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

    public Integer updatePrice(String user, Float price){

        int Worked = 0;

        Worked = dogDaycareRepository.updatePrice(price,user);

        return Worked;

    }

    public Integer updateTax(String user, Float tax){

        int Worked = 0;

        Worked = dogDaycareRepository.updateTax(tax,user);

        return Worked;

    }

    @PostMapping("/update")
    public DogDayCare_Dto updateAll(@Valid @RequestBody DogDayCare_Dto user_dto){

        System.out.println(user_dto);

        DogDayCare_Dto DayCare_DTO = user_dto;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(user_dto.getUser());

        if (checkUser!=null)
        {

            uppdateReturns = updateUserPassword(user_dto.getUser(),user_dto.getPassword());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setPassword(null);
            }
            DayCare_DTO.setPassword(null);
            uppdateReturns = updateAddress(user_dto.getUser(),user_dto.getDog_daycare_address());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_address(null);
            }

            uppdateReturns = updateName(user_dto.getUser(),user_dto.getDog_daycare_name());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_name(null);
            }

            uppdateReturns = updatePhone(user_dto.getUser(),user_dto.getDog_daycare_phone());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_phone(null);
            }

            uppdateReturns = updateMail(user_dto.getUser(),user_dto.getDog_daycare_e_mail());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_e_mail(null);
            }

            uppdateReturns = updateType(user_dto.getUser(),user_dto.getDog_daycare_type());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_type(null);
            }

            uppdateReturns = updatePrice(user_dto.getUser(),user_dto.getDog_daycare_price_base());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_price_base(null);
            }

            uppdateReturns = updateTax(user_dto.getUser(),user_dto.getDog_daycare_tax());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_tax(null);
            }

        }else{
            DayCare_DTO = new DogDayCare_Dto();
        }
        DayCare_DTO.setRole(user_dto.getRole());
        DayCare_DTO.setToken(user_dto.getToken());

        return DayCare_DTO;

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