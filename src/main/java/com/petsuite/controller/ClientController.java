package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;

import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.*;
import com.petsuite.Services.repository.*;
import com.petsuite.basics.Cadena;
import com.petsuite.basics.CadenaDoble;

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
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/load")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public Client_Dto createClient(@Valid @RequestBody Client_Dto client) {

        System.out.println("Entramos al load client");
        if(!infoUserRepository.existsById(client.getUser())){
            Client realClient=new Client(client.getClient_address(),
                    null,null,null,null);
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

    public Integer updateAddress(String user, String address){

        int Worked = 0;

        Worked = clientRepository.updateAddressByUser(address,user);

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

    @PostMapping("/update")
    public Client_Dto updateAll(@Valid @RequestBody Client_Dto user_dto){

        System.out.println(user_dto);

        Client_Dto Cli_Dto = user_dto;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(user_dto.getUser());

        if (checkUser!=null)
        {

            uppdateReturns = updateUserPassword(user_dto.getUser(),user_dto.getPassword());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setPassword(null);
            }
            Cli_Dto.setPassword(null);//porque no es bueno que le devolvamos la contraseña

            uppdateReturns = updateAddress(user_dto.getUser(),user_dto.getClient_address());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_address(null);
            }

            uppdateReturns = updateName(user_dto.getUser(),user_dto.getClient_name());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_name(null);
            }

            uppdateReturns = updatePhone(user_dto.getUser(),user_dto.getClient_phone());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_phone(null);
            }

            uppdateReturns = updateMail(user_dto.getUser(),user_dto.getClient_e_mail());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_e_mail(null);
            }

        }else{
            Cli_Dto = new Client_Dto();
        }
        
        Cli_Dto.setRole(user_dto.getRole());
        Cli_Dto.setToken(user_dto.getToken());
        
        return Cli_Dto;

    }

    @PostMapping("/searchdaycarebyname")
    public List<DogDayCare_Dto> searchDayCareByName(@Valid @RequestBody Cadena name){

        System.out.println(name);

        List<String> usersToReturns = new ArrayList<>();

        List<String> findings = new ArrayList<>();

        String[] Words = name.getCadena().split(" ");

        for (int i=0; i<Words.length; i++){
            System.out.println(Words);
            findings = dogDaycareRepository.searchByName("%"+Words[i]+"%");

            while(!findings.isEmpty()){
                if (!usersToReturns.contains(findings.get(0))) {
                    usersToReturns.add(findings.get(0));
                }
                findings.remove(0);
            }
        }

        List<DogDayCare_Dto> returns = new ArrayList<>();

        DogDayCare_Dto DTO;

        Optional<DogDaycare> DC;

        while(!usersToReturns.isEmpty()){

            DC = dogDaycareRepository.findById(usersToReturns.remove(0));

            System.out.println(DC.get().getUser());

            DTO = new DogDayCare_Dto();

            DTO.setDog_daycare_name(DC.get().getName());
            DTO.setDog_daycare_type(DC.get().getDog_daycare_type());
            DTO.setDog_daycare_score(DC.get().getDog_daycare_score());
            DTO.setDog_daycare_phone(DC.get().getPhone());
            DTO.setDog_daycare_address(DC.get().getDog_daycare_address());
            DTO.setUser(DC.get().getUser());
            DTO.setDog_daycare_e_mail(DC.get().getE_mail());
            DTO.setDog_daycare_price_base(DC.get().getDog_daycare_base_price());
            DTO.setDog_daycare_tax(DC.get().getDog_daycare_tax());

            returns.add(DTO);

        }

        return returns;
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



