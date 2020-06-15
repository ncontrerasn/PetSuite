package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.*;
import com.petsuite.Services.model.WalkPetition;
import java.util.List;

public interface IRequestPetition {

    WalkPetition_Dto createPeititon( WalkPetition_Dto walkPetition); //create de WalkpetitionController
    List<WalkPetition> myPetition(String user);
    DogWalker_Dto walkerInPetition(Cadena user);
    void deletePetition(Integer Petition_id);
    Boolean denyOrAcceptPetition(WalkInvoice_Dto walkInvoice_Dto);
    
}
