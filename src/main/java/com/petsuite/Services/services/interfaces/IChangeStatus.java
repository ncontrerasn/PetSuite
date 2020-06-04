/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.model.WalkInvoice;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author sergi
 */
public interface IChangeStatus {
    
    Boolean endCare (Entero DogDayCareInoviceId);
    List<WalkInvoice> updateInvoiceStatus( Entero entero )throws InterruptedException;// updateInvoiceStatus de WalkInvoiceController
    
}
