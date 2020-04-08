/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author sergi
 */
@Data
@AllArgsConstructor
public class InfoUser_Dto {
      
    private String user;
    
   
    private String password;
   
   private Integer type;//tipo 1 cliente, tipo 2 paseador, tipo 3 paseador;
    
    
}
