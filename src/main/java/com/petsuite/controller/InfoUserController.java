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
import com.petsuite.Services.dto.User_Dto;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.repository.ClientRepository;
import com.petsuite.Services.repository.InfoUserRepository;
import com.petsuite.basics.Entero;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
public class InfoUserController {

    @Autowired
    InfoUserRepository infoUserRepository;
    @Autowired 
    ClientController clientController;
    @Autowired 
    DogDayCareController dogDaycareController;
    @Autowired 
    DogWalkerController dogWalkerController;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<InfoUser> getAllUsers() {
        return infoUserRepository.findAll();
    }
    
    @PostMapping("/loadClient")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public InfoUser createClient(@Valid @RequestBody Client object, @RequestBody InfoUser  infoUser) {
        System.out.println("El usuario es: "+ infoUser.getUser());
        String sqlB = "SELECT count(*) as users FROM info_user where user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{infoUser.getUser()}, (rs, rowNum) -> new Entero(
                rs.getInt("users")
        ));
        String sqlA = "SELECT * FROM info_user where user = ?";
        if(ul2.get(0).getEntero()==0){
            
            if(object instanceof Client) {
               
              
           // infoUser.setClient(object);
            infoUserRepository.save(infoUser);
            //clientController.createClient(object, infoUser);
            
            
            
            }
          /*  if(object instanceof DogWalker){
                
               infoUser.setDogWalker((DogWalker) object);
            infoUserRepository.save(infoUser);
            dogWalkerController.createWalker((DogWalker) object, infoUser);
            }
            if(object instanceof DogDaycare){
                
                infoUser.setDogDaycare((DogDaycare) object);
            infoUserRepository.save(infoUser);
            dogDaycareController.createDogDaycare((DogDaycare) object, infoUser);
            }
        
      */
        
        
     
    }
        
        return infoUser;
    }
    
    @RequestMapping(value="/login")
    @ResponseBody
    public Object clientLogin(@Valid @RequestBody InfoUser_Dto user){
        
        
        
     String sqlA = "SELECT * FROM info_user where user = ?";
        String user__user = user.getUser();
        String user_password = user.getPassword();
        
        
       
        List<InfoUser> ul= jdbcTemplate.query(sqlA, new Object[]{user.getUser()}, (rs, rowNum) -> new InfoUser(
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getInt("type")
                      
                ));
        InfoUser u;
        if (ul.isEmpty()==false){
            u = ul.get(0);
            if (u.getPassword().equals(user_password)){
                
                
                
                
                
                if(u.getType()==1){
                    String sqlC = "SELECT * FROM info_user natural join client where user = ?";
                    List<Client_Dto> ul2= jdbcTemplate.query(sqlC, new Object[]{user.getUser()}, (rs, rowNum) -> new Client_Dto(
                        rs.getString("client_name"),
                        rs.getString("client_phone"),
                        rs.getString("client_e_mail"),
                        rs.getString("client_address")
                      
                    ));
                    
                    if(ul2.get(0)!=null) return ul2.get(0);
                    
                    
                    
                }
                if(u.getType()==2){
                    String sqlP = "SELECT * FROM info_user natural join dog_walker where user = ?";
                    List<DogWalker_Dto> ul2= jdbcTemplate.query(sqlP, new Object[]{user.getUser()}, (rs, rowNum) -> new DogWalker_Dto(
                        rs.getString("dog_walker_name"),
                        rs.getString("dog_walker_phone"),
                        rs.getString("dog_walker_e_mail"),
                        rs.getFloat("dog_walker_score")
                            
                      
                    ));
                    
                    if(ul2.get(0)!=null) return ul2.get(0);
                    
                    
                }
                if(u.getType()==3){
                    String sqlG = "SELECT * FROM info_user natural join dog_daycare where client_user = ?";
                    List<DogDayCare_Dto> ul2= jdbcTemplate.query(sqlA, new Object[]{user.getUser()}, (rs, rowNum) -> new DogDayCare_Dto(
                        rs.getString("dog_daycare_e_mail"),
                        rs.getString("dog_daycare_address"),
                        rs.getBoolean("dog_daycare_type"),
                        rs.getString("dog_daycare_phone"),
                        rs.getFloat("dog_daycare_score"),
                        rs.getString("dog_daycare_name")
                            
                      
                    ));
                    
                      if(ul2.get(0)!=null) return ul2.get(0);
                    
                    
                    
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
       


 

