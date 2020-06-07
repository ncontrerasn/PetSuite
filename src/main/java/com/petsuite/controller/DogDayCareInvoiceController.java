package com.petsuite.controller;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.model.DogDayCareService_DogDayCareInvoice;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.*;
import com.petsuite.Services.services.QualifyService;
import com.petsuite.Services.services.ChangeStatusService;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Entero;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Autowired
    Service_InvoiceRepository service_InvoiceRepository;

    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    QualifyService dogDayCareQualificationService;
    
    //Importamos el servicio a ustilizar de erminar el servicio de cuidado
    @Autowired
    ChangeStatusService endCareService;
  
    @GetMapping("/all")
    public List<DogDaycareInvoice> getAllClients() {
      return dogDaycareInvoiceRepository.findAll();
    }

    @PostMapping("/endService")//Vamos a terminar el servicio del cuidado
    public Boolean endCareSerice(@Valid @RequestBody Entero idDogDayCareInovice) {
        System.out.println("El id que me llega es: "+ idDogDayCareInovice);
        //Llamamos al servicio
        return endCareService.endCare(idDogDayCareInovice);
    }
    
    
    @PostMapping("/load")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCareInvoice_Dto createDogDayCareInvoice(@Valid @RequestBody DogDayCareInvoice_Dto dogDaycareInovice) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dogDaycareInovice.getDog_daycare_invoice_date(), formatter);
        float price=requestPriceDogDayCareInvoice(dogDaycareInovice).getDog_daycare_invoice_price();
        DogDaycareInvoice daycareInvoice= new DogDaycareInvoice(null, dateTime,
                dogDaycareInovice.getDog_daycare_invoice_duration(), price, "Aceptado",
                dogDaycareInovice.getDog_daycare_invoice_dogdaycare_id(), dogDaycareInovice.getDog_daycare_invoice_client_id(),
                dogDaycareInovice.getDog_daycare_invoice_dog_id(), null, null, null,
                null, null);
        dogDaycareInovice.setDog_daycare_invoice_price(price);
         if(daycareInvoice!=null){
           DogDaycareInvoice dogDaycareInvoice = dogDaycareInvoiceRepository.saveAndFlush(daycareInvoice);
            //registro en la tabla intermedia
            for (int i = 0; i < dogDaycareInovice.getDog_daycare_invoice_services().size(); i++) {
                DogDayCareService_DogDayCareInvoice dogDayCareService_dogDayCareInvoice = new DogDayCareService_DogDayCareInvoice();
                dogDayCareService_dogDayCareInvoice.setDogDaycareInvoice(dogDaycareInvoice);
                dogDayCareService_dogDayCareInvoice.setDogDaycareService(dogDaycareServiceRepository.findById(
                        dogDaycareInovice.getDog_daycare_invoice_services().get(i)).get());
                service_InvoiceRepository.save(dogDayCareService_dogDayCareInvoice);
            }
         }
        return dogDaycareInovice;
    }
    
    
    
    

    @PostMapping("/consultPrice")//Retorna una estructura de tipo DogDaycare vacia si ya esta utilizado el nombre de usuario
    public DogDayCareInvoice_Dto requestPriceDogDayCareInvoice(@Valid @RequestBody DogDayCareInvoice_Dto dogDaycareInovice) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dogDaycareInovice.getDog_daycare_invoice_date(), formatter);
        float dogWeight = dogRepository.findByDogId(dogDaycareInovice.getDog_daycare_invoice_dog_id()).getDog_weight();
        DogDaycare dogDaycare = dogDaycareRepository.findById(dogDaycareInovice.getDog_daycare_invoice_dogdaycare_id()).get();
        //precio base por la estadía
        Float price = dogDaycareInovice.getDog_daycare_invoice_duration() * dogDaycare.getDog_daycare_base_price();
        //perro pesado es mayor o igual que 28 kg
        if(dogWeight >= 28)
            price += dogDaycareInovice.getDog_daycare_invoice_duration() * dogDaycare.getDog_daycare_tax();
        //más el precio por los servicios
        for (int i = 0; i < dogDaycareInovice.getDog_daycare_invoice_services().size(); i++)
            price += dogDaycareServiceRepository.findById(
                    dogDaycareInovice.getDog_daycare_invoice_services().get(i)).get().getDogdaycare_Service_Price();
        //Asigna un precio
        dogDaycareInovice.setDog_daycare_invoice_price(price);
        return dogDaycareInovice;
        
        
    }

    @PostMapping("/score")
    public Cadena scoreDogDayCare(@Valid @RequestBody DogDayCareInvoice_Dto dogDayCareInvoice_dto){
        return dogDayCareQualificationService.qualifyDogDayCare(dogDayCareInvoice_dto);
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