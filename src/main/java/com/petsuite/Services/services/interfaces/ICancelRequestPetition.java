package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.Cancellation_Dto;

public interface ICancelRequestPetition {
    
    Boolean cancelCare(Cancellation_Dto cancellation_Dto);
    Boolean cancelWalk(Cancellation_Dto cancellation_Dto);
    
}
