package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.WalkPetition_Dto;

import java.util.List;

public interface IShowWalkPetition {

    List<WalkPetition_Dto> finPetitionByUserWithProposalPrice(Cadena user);

}
