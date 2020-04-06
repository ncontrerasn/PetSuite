/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.controller;

import com.petsuite.Services.model.Client;
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
    public Client createClient(@Valid @RequestBody Client client) {
        String sqlB = "SELECT count(*) as walkers FROM dog_walker where dog_walker_user = ?";
        List<Entero> ul2= jdbcTemplate.query(sqlB, new Object[]{client.getClient_user()}, (rs, rowNum) -> new Entero(
                rs.getInt("walkers")
        ));
        sqlB = "SELECT count(*) as clients FROM client where client_user = ?";
        List<Entero> ul3= jdbcTemplate.query(sqlB, new Object[]{client.getClient_user()}, (rs, rowNum) -> new Entero(
                rs.getInt("clients")
        ));
        sqlB = "SELECT count(*) as daycares FROM dog_daycare where dog_daycare_user = ?";
        List<Entero> ul4= jdbcTemplate.query(sqlB, new Object[]{client.getClient_user()}, (rs, rowNum) -> new Entero(
                rs.getInt("daycares")
        ));
        if(ul2.get(0).getEntero()==0 && ul3.get(0).getEntero()==0 && ul4.get(0).getEntero()==0){
        
        String sqlA = "SELECT * FROM client where client_user = ?";
        List<Client> ul= jdbcTemplate.query(sqlA, new Object[]{client.getClient_user()}, (rs, rowNum) -> new Client(
                        rs.getString("client_user"),
                        rs.getString("client_password"),
                        rs.getString("client_name"),
                        rs.getInt("client_phone"),
                        rs.getString("client_e_mail"),
                        rs.getString("client_address"),
                        null,
                        null,
                        null,
                        null
                ));
        return clientRepository.save(client);
        }
        return new Client();
    }
    
    @RequestMapping(value="/login")
    @ResponseBody
    public boolean clientLogin(@Valid @RequestBody Client client){
        
        String sqlA = "SELECT * FROM client where client_user = ?";
        String client_user = client.getClient_user();
        String client_password = client.getClient_password();
        List<Client> ul= jdbcTemplate.query(sqlA, new Object[]{client_user}, (rs, rowNum) -> new Client(
                        rs.getString("client_user"),
                        rs.getString("client_password"),
                        rs.getString("client_name"),
                        rs.getInt("client_phone"),
                        rs.getString("client_e_mail"),
                        rs.getString("client_address"),
                        null,
                        null,
                        null,
                        null
                ));
        Client u;
        if (ul.isEmpty()==false){
            u = ul.get(0);
            if (u.getClient_password().equals(client_password)){
                return true;
            }
        }
        return false;
    }
}
