/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.repository.ClientRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.InfoUserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author huber
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class InfoUserController {

    @Autowired
    InfoUserRepository infoUserRepository;
    @Autowired
    DogWalkerRepository dogWalkerRepository ;
    
     @Autowired
    DogDaycareRepository dogDaycareRepository ;
     @Autowired
    ClientRepository clientRepository;
    @Autowired 
    ClientController clientController;
    @Autowired 
    DogDayCareController dogDaycareController;
    @Autowired 
    DogWalkerController dogWalkerController;
    @Autowired 
    TokenController tokenController;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<InfoUser> getAllUsers() {
        return infoUserRepository.findAll();
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object clientLogin(@Valid @RequestBody InfoUser_Dto user){
   
       String user_user = user.getUser();
        String user_password = user.getPassword();

        List<InfoUser> ul= infoUserRepository.findUserbyUser(user_user);
        InfoUser u;
        if (!ul.isEmpty()){
            u = ul.get(0);
            if (u.getPassword().equals(user_password)){
                if("ROLE_CLIENT".equals(u.getRole())){
                    List<Client> clients=clientRepository.findClientbyUser(user_user);
                    List<Client_Dto> ul2= new ArrayList<>();
                    for(int i=0; i<clients.size();i++){
                      Client_Dto myClient_Dto= new Client_Dto(clients.get(i).getName(), clients.get(i).getPhone(), clients.get(i).getE_mail(), clients.get(i).getClient_address());
                      ul2.add(myClient_Dto);
                    }               
                  
                    if(ul2.get(0)!=null)
                    {
                        user.setRole(u.getRole());
                        String token= tokenController.generate(user);
                        ul2.get(0).setUser(u.getUser());
                        ul2.get(0).setToken(token);
                        ul2.get(0).setRole(u.getRole());
                        return ul2.get(0);
                    }
                }
                if("ROLE_DOGWALKER".equals(u.getRole())){
                   List<DogWalker> dogWalkers=dogWalkerRepository.findWalkerByUser(user_user);
                   List<DogWalker_Dto> ul2= new ArrayList<>();
                   for(int i=0; i<dogWalkers.size();i++){
                      DogWalker_Dto dogWalker_Dto = new DogWalker_Dto(dogWalkers.get(i).getName(), dogWalkers.get(i).getPhone(), dogWalkers.get(i).getE_mail(), dogWalkers.get(i).getDog_walker_score());
                      ul2.add(dogWalker_Dto);                        
                    }      
                   
                    if(ul2.get(0)!=null)
                    {
                         user.setRole(u.getRole());
                        String token = tokenController.generate(user);
                        ul2.get(0).setToken(token);
                        ul2.get(0).setUser(u.getUser());
                        ul2.get(0).setRole(u.getRole());
                        
                        return ul2.get(0);
                    }
                }
                if("ROLE_DOGDAYCARE".equals(u.getRole())){

                    List<DogDaycare> daycares=dogDaycareRepository.findDogDayCareByUser(user_user);
                    List<DogDayCare_Dto> ul2=new ArrayList<>();
                     for(int i=0; i<daycares.size();i++){
                         DogDayCare_Dto dayCare_Dto= new DogDayCare_Dto(daycares.get(i).getE_mail(),daycares.get(i).getDog_daycare_address() , daycares.get(i).getDog_daycare_type(), daycares.get(i).getPhone(), daycares.get(i).getDog_daycare_score(), daycares.get(i).getName(), daycares.get(i).getDog_daycare_base_price(), daycares.get(i).getDog_daycare_tax());
                         ul2.add(dayCare_Dto);
                    }                   
                      if(ul2.get(0)!=null)
                      {
                           user.setRole(u.getRole());
                          String token = tokenController.generate(user);
                          ul2.get(0).setToken(token);
                          ul2.get(0).setUser(u.getUser());
                          ul2.get(0).setRole(u.getRole());
                          return ul2.get(0);
                      }
                }
            }
        }
        return null;
    }

    private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
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
       


 

