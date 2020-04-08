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
public class DogDayCare_Dto {
    
     private String user;
    private String password;
    
    private String dog_daycare_e_mail;
    
private String dog_daycare_name;

    

    private String dog_daycare_address;
    

    private Boolean dog_daycare_type;
    

    private String dog_daycare_phone;
    

    private Float dog_daycare_score;

    public DogDayCare_Dto(String dog_daycare_e_mail, String dog_daycare_address, Boolean dog_daycare_type, String dog_daycare_phone, Float dog_daycare_score, String dog_daycare_name) {
        this.dog_daycare_e_mail = dog_daycare_e_mail;
        this.dog_daycare_address = dog_daycare_address;
        this.dog_daycare_type = dog_daycare_type;
        this.dog_daycare_phone = dog_daycare_phone;
        this.dog_daycare_score = dog_daycare_score;
        this.dog_daycare_name=dog_daycare_name;
    }
    
    
    
}
