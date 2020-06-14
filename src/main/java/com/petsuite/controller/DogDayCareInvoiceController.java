package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.Notification;
import com.petsuite.Services.services.*;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Cancellation_Dto;
import com.petsuite.Services.dto.Dog_Dto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dog_day_care_invoices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class DogDayCareInvoiceController {

    @Autowired
    QualifyService dogDayCareQualificationService;
    
    @Autowired
    FindDogService findDogService;

    @Autowired
    ChangeStatusRequestPetitionService endCareService;

    @Autowired
    GetAllDataService getAllData;

    @Autowired
    CreateInvoiceService createInvoiceService;

    @Autowired
    ProposePriceService proposePrice;
    
    @Autowired
    CancelRequestPetitionService  cancelRequestPetitionService;
    
    @Autowired
    ChangeStatusRequestPetitionService changeStatusRequestPetitionService;

    @Autowired
    CreateNotificationService createNotificationService;
    
    @GetMapping("/all")
    public List<DogDaycareInvoice> getAllInvoices() { return getAllData.getAllInvoices(); }
    
    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCareInvoice_Dto createDogDayCareInvoice(@Valid @RequestBody DogDayCareInvoice_Dto dogDaycareInovice) { 
       int dogId = dogDaycareInovice.getDog_daycare_invoice_dog_id();
        Entero entero1 = new Entero(dogId);
        Dog_Dto dog = findDogService.find(entero1);  
      DogDayCareInvoice_Dto careInvoice_Dto=  createInvoiceService.createDogDayCareInvoice(dogDaycareInovice);
      if(careInvoice_Dto.getDog_daycare_invoice_status().equals("Aceptado"))
        createNotificationService.createNotification(new Notification(null, "Un nuevo usuario desea tus Servicios",
                    dogDaycareInovice.getDog_daycare_invoice_client_id()+" desea contratarte para que cuides a su perro "+ dog.getDog_name()+".", "No leido", dogDaycareInovice.getDog_daycare_invoice_dogdaycare_id(), null));
        
        return dogDaycareInovice;
    }

    @PostMapping("/consultPrice")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCareInvoice_Dto requestPriceDogDayCareInvoice(@Valid @RequestBody DogDayCareInvoice_Dto dogDaycareInovice) { return proposePrice.requestPriceDogDayCareInvoice(dogDaycareInovice); }

    @PostMapping("/score")
    public Cadena scoreDogDayCare(@Valid @RequestBody DogDayCareInvoice_Dto dogDayCareInvoice_dto){ return dogDayCareQualificationService.qualifyDogDayCare(dogDayCareInvoice_dto); }
    //Para cambiar los estados al recibo de la guardería. Se recibe el id del recibo
    @PostMapping("/updateCareInvoiceStatus")
    public DogDaycareInvoice updateInvoiceStatus(@Valid @RequestBody Entero entero) throws InterruptedException{ 
        
        DogDaycareInvoice daycareInvoice = getAllData.findInvoiceById(entero.getEntero()).get();
        int dogId = daycareInvoice.getDog_id();
        Entero entero1 = new Entero(dogId);
        Dog_Dto dog = findDogService.find(entero1);
        DogDaycareInvoice daycareInvoice1=changeStatusRequestPetitionService.updateCareInvoiceStatus(entero);
        createNotificationService.createNotification(new Notification(null, "Tienes una actualización en el estado de uno de tus cuidados",
                "El estado del cuidado de tu perro " + dog.getDog_name() +" ha sido actualizado a " + daycareInvoice.getDog_daycare_invoice_status()+".", "No leido", daycareInvoice.getClient_id(), null));
        
        return  daycareInvoice1;
                }
    
    @PostMapping(value = "/cancelPetition")
    public Boolean cancelPetition(@Valid @RequestBody Cancellation_Dto cancellation_Dto){
        boolean res = cancelRequestPetitionService.cancelCare(cancellation_Dto);
        if(res)
            createNotificationService.createNotification(new Notification(null, "Se ha cancelado uno de tus cuidados",
                    cancellation_Dto.getUser_whoCancel() +" ha cancelado el servicio que tenía contigo.", "No leido", cancellation_Dto.getUser_Cancelled(), null));
        return res;
    }

    @PostMapping(value = "/myCurrentDogList")
    public List<Dog_Dto> getMyCurrentDogList(@Valid @RequestBody Cadena cadena){
        List<Integer> integers = getAllData.getCurrentDogIdListInChargeOfDogDayCare(cadena.getCadena());
        List<Dog_Dto> dog_dtos = new ArrayList<>();
        for(int i = 0; i < integers.size(); i++){
            Entero entero = new Entero(integers.get(i));
            dog_dtos.add(findDogService.find(entero));
        }
        return dog_dtos;
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