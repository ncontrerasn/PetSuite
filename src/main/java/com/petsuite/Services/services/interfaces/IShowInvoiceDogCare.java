package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.basics.CadenaDoble;
import java.util.List;

public interface IShowInvoiceDogCare {
    
    List<DogDayCareInvoice_Dto> showInovicesByStatus(CadenaDoble cadenaDoble);
    
}
