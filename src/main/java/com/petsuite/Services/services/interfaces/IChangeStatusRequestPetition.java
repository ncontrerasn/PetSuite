package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.WalkInvoice;
import java.util.List;
import java.util.Optional;

public interface IChangeStatusRequestPetition {

    Optional<WalkInvoice> updateWalkInvoiceStatus(Entero entero);
    DogDaycareInvoice updateCareInvoiceStatus(Entero entero);
    
}
