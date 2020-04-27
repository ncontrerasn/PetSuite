/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.*;

/**
 *
 * @author sergi
 */
@Data
@AllArgsConstructor
public class InfoUser_Dto {
      
    private String user;

    private String password;
   
    private String role;//tipo 1 cliente, tipo 2 paseador, tipo 3 paseador;
    
    
     
    /*private String e_mail;

    
    private String phone;
    
    
    
    private String name;*/


}
