package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.*;
import com.petsuite.Services.repository.*;
import com.petsuite.Services.services.ChangeStatusService;
import com.petsuite.Services.services.SearchDogDayCareService;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;

import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.ClientRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import com.petsuite.Services.services.ShowInvoiceDogCareService;

import com.petsuite.Services.services.UpdateService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ClientController {

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Autowired
    ClientRepository clientRepository;
    
    
    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;
    
    @Autowired
    DogDaycareRepository dogdaycareRepository;

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    DogWalkerRepository dogWalkerRepository;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    InfoUserRepository infoUserRepository;

    @Autowired
    SearchDogDayCareService searchDogDayCare;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    ShowInvoiceDogCareService showInvoiceDogCare;

    @Autowired
    UpdateService updateService;

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    
    @PostMapping("/seeEndedCares")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public List<DogDayCareInvoice_Dto> showInovicesByStatus(@Valid @RequestBody CadenaDoble cadena) {
        System.out.println(cadena.getCadena1() + cadena.getCadena2());
    return showInvoiceDogCare.showInovicesByStatus(cadena);        
        
    }
    
    @PostMapping("/load")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public Client_Dto createClient(@Valid @RequestBody Client_Dto client) {

               if(!infoUserRepository.existsById(client.getUser())){
            Client realClient=new Client();
            realClient.setUser(client.getUser());
            realClient.setPassword(client.getPassword());
            realClient.setRole("ROLE_CLIENT");
            realClient.setClient_address(client.getClient_address());
            realClient.setPhone(client.getClient_phone());
            realClient.setName(client.getClient_name());
            realClient.setE_mail(client.getClient_e_mail());
            clientRepository.save(realClient);
            return client;
        }
        return null;
    }

    @PostMapping("/dogList")
    public List<Dog> myDogList(@Valid @RequestBody Cadena user){
        return dogRepository.findByUser(user.getCadena());
    }

    @GetMapping("/allDogDayCares")
    public List<DogDayCare_Dto> getAllDogDayCares() {
         System.out.println("Diego esta solicitando todas las guarderias");
        List<DogDaycare> lista=dogdaycareRepository.findAll();
        List<DogDayCare_Dto> listaEnviar= new ArrayList<>();
         for (int i = 0; i < lista.size(); i++) {
             DogDayCare_Dto  guarderia = new DogDayCare_Dto(lista.get(i).getE_mail(), lista.get(i).getDog_daycare_address(), lista.get(i).getDog_daycare_type(), lista.get(i).getPhone(), lista.get(i).getDog_daycare_score(), lista.get(i).getName(), lista.get(i).getDog_daycare_base_price(), lista.get(i).getDog_daycare_tax());
             guarderia.setUser(lista.get(i).getUser());
             listaEnviar.add(guarderia);
         }
         return listaEnviar;
    }
    
    @GetMapping("/myServicesAvailables")
    public List<DogDaycareService> getMyServices(@RequestParam(value = "user") String user) {
        System.out.println("Diego esta verficiando los servicios como cliente");
        return dogDaycareServiceRepository.findMyServicesByUser(user);
    }

    @PostMapping("/mypetition")
    public List<WalkPetition> myPetition(@Valid @RequestBody String user){
        return walkPetitionRepository.findPetitionsByUser(user);
    }

    @PostMapping("/update")
    public Client_Dto updateAll(@Valid @RequestBody Client_Dto user_dto){

        return  updateService.UpdateClient(user_dto);

    }

    @PostMapping("/searchdaycarebyname")
    public List<DogDayCare_Dto> searchDayCareByNameAndService(@Valid @RequestBody Cadena name){

        return searchDogDayCare.searchDayCareByNameAndService(name);

    }

    @PostMapping("/mywalker")
    public DogWalker_Dto walkerInPetition(@Valid @RequestBody Cadena user){

        Optional<DogWalker> DWopt = dogWalkerRepository.findById(user.getCadena());

        DogWalker_Dto DW_DTO = new DogWalker_Dto();

        DW_DTO.setDog_walker_e_mail(DWopt.get().getE_mail());
        DW_DTO.setDog_walker_name(DWopt.get().getName());
        DW_DTO.setDog_walker_phone(DWopt.get().getPhone());
        DW_DTO.setDog_walker_score(DWopt.get().getDog_walker_score());
        DW_DTO.setUser(DWopt.get().getUser());

        return DW_DTO;
    }

    public String getClientJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("CLIENT");

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



