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
@AllArgsConstructor
@NoArgsConstructor
public class DogWalker_Dto {
     
    private String user;

    private String password;

    private String dog_walker_name;

    private String dog_walker_phone;

    private String dog_walker_e_mail;

    private Float dog_walker_score;

    private String token;
    private String role;

    public DogWalker_Dto(String dog_walker_name, String dog_walker_phone, String dog_walker_e_mail, Float dog_walker_score) {
        this.dog_walker_name = dog_walker_name;
        this.dog_walker_phone = dog_walker_phone;
        this.dog_walker_e_mail = dog_walker_e_mail;
        this.dog_walker_score = dog_walker_score;
    }
    
    
}
