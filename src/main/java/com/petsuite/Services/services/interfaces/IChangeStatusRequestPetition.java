/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.WalkInvoice;
import java.util.List;

/**
 *
 * @author sergi
 */
public interface IChangeStatusRequestPetition {
    
  //  Boolean endCare (Entero DogDayCareInoviceId);
    List<WalkInvoice> updateWalkInvoiceStatus(Entero entero);
    DogDaycareInvoice updateCareInvoiceStatus(Entero entero);
    
}
