package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.WalkInvoice;
import java.util.List;

public interface IChangeStatusRequestPetition {

    List<WalkInvoice> updateWalkInvoiceStatus(Entero entero);
    DogDaycareInvoice updateCareInvoiceStatus(Entero entero);
    
}
