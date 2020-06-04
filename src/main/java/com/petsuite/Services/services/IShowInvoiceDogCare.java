/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.basics.CadenaDoble;
import java.util.List;

/**
 *
 * @author sergi
 */
public interface IShowInvoiceDogCare {
    
    public List<DogDayCareInvoice_Dto> showInovicesByStatus(CadenaDoble cadenaDoble);
    
}
