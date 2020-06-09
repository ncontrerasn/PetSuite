/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.*;
import com.petsuite.Services.model.WalkPetition;

import java.util.List;

/**
 *
 * @author sergi
 */
public interface IRequestPetition {

    WalkPetition_Dto createPeititon( WalkPetition_Dto walkPetition); //create de WalkpetitionController
    List<WalkPetition> myPetition(String user);
    DogWalker_Dto walkerInPetition(Cadena user);
    void deletePetition(Integer Petition_id);
    Dog_Dto denyOrAcceptPetition(WalkInvoice_Dto walkInvoice_Dto);
    
}
