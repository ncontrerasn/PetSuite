/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.controller;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.repository.ClientRepository;
import com.petsuite.basics.Entero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author huber
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    @PostMapping("/load")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public Client_Dto createClient(@Valid @RequestBody Client_Dto client) {
        
        String sqlB = "SELECT count(*) as users FROM info_user where user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{client.getUser()}, (rs, rowNum) -> new Entero(
                rs.getInt("users")
        ));
        //String sqlA = "SELECT * FROM info_user where user = ?";
        if(ul2.get(0).getEntero()==0){
            
        Client realClient=new Client(client.getClient_name(), client.getClient_phone(), client.getClient_e_mail(), client.getClient_address(), null, null, null, null);
        realClient.setUser(client.getUser());
        realClient.setPassword(client.getPassword());
        realClient.setType(1);
        
            
         clientRepository.save(realClient);
         return client;
        }
   return null;
    }
        
    
    
    @RequestMapping(value="/login")
    @ResponseBody
    public boolean clientLogin(@Valid @RequestBody Client client){
     
        return false;

    }


 
}

