/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.dto;

import java.time.LocalDateTime;
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
public class WalkPetition_Dto {
    
    
    private LocalDateTime walk_petition_date_time;
    
    
    private String walk_petition_address;
    
    
    private Float walk_petition_duration;
    
    private String walk_petition_notes;
    
    private String user;
    
    private Integer dog_id;

    
}
