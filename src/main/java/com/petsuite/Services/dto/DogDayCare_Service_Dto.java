/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class DogDayCare_Service_Dto {
    
    
 @NotBlank
    private String dog_daycare_invoice_name;
    
    private String dog_daycare_invoice_description;

    @NotNull
    private float dog_daycare_invoice_price;
    
    @NotBlank
    private String dog_daycare_user;
    
    @NotNull
    private Integer dog_daycare_invoice_id;
    
     @NotNull
    private Integer dog_daycare_id;
     

    
    
    
    
}
