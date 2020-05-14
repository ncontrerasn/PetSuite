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
    private String dogdaycare_Service_Name;
    
    private String dogdaycare_Service_Description;

    @NotNull
    private float dogdaycare_Service_Price;
    
    @NotBlank
    private String dogdaycare_Service_ClientId;
    
    /*@NotNull
    private Integer dog_daycare_invoice_id;*/
    
   
     

    
    
    
    
}
