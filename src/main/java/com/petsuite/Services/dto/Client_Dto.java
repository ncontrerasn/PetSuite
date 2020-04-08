/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sergi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client_Dto {
    
    
    private String user;
    private String password;
 
    private String client_name;
  
    private String client_phone;
    
   
    private String client_e_mail;
    
  
    private String client_address;
    
    

    public Client_Dto(String client_name, String client_phone, String client_e_mail, String client_address) {
        this.client_name = client_name;
        this.client_phone = client_phone;
        this.client_e_mail = client_e_mail;
        this.client_address = client_address;
    }
    
    
    
    
}
