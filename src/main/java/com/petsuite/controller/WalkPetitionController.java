package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.basics.Cadena;

import com.petsuite.Services.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/walkpetitions")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class WalkPetitionController {

    @Autowired
    GetAllData getAllData;

    @Autowired
    RequestPetitionService requestPetitionService;

    @Autowired
    FindDogService findDogService;

    @Autowired
    ShowWalkPetitionService showWalkPetitionService;

    @Autowired
    ProposePrice proposePrice;

    @GetMapping("/all")
    public List<WalkPetition_Dto> getAllPetitions() { return getAllData.getAllPetitions(); }

    @PostMapping("/create")
    public WalkPetition_Dto createPeititon(@Valid @RequestBody WalkPetition_Dto walkPetition){ return requestPetitionService.createPeititon(walkPetition); }

    @DeleteMapping("/abort")
    @ResponseBody
    public void deletePetition(@Valid @RequestBody Integer Petition_id){ requestPetitionService.deletePetition(Petition_id); }

    @PostMapping("/findbydog")
    public List<WalkPetition> finPetitionByDog(@Valid @RequestBody Cadena dog){ return findDogService.finPetitionByDog(dog); }

    @PostMapping("/findbyuser")
    public List<WalkPetition_Dto> finPetitionByUserWithProposalPrice(@Valid @RequestBody Cadena user){ return showWalkPetitionService.finPetitionByUserWithProposalPrice(user); }

    @PostMapping("/propose")
    public  Dog_Dto proposePrice(@Valid @RequestBody WalkPetition_Dto walkPetition_Dto){ return proposePrice.proposePrice(walkPetition_Dto); }

    @PostMapping("/denyoraccept")
    public  Dog_Dto denyOrAcceptPetition(@Valid @RequestBody WalkInvoice_Dto walkInvoice_Dto){ return requestPetitionService.denyOrAcceptPetition(walkInvoice_Dto); }

}

