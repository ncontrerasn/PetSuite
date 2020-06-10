/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.Cancellation_Dto;

/**
 *
 * @author sergi
 */
public interface ICancelRequestPetition {
    
    Boolean cancelCare(Cancellation_Dto cancellation_Dto);
    Boolean cancelWalk(Cancellation_Dto cancellation_Dto);
    
}
