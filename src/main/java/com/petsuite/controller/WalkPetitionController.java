package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.Notification;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.Entero;
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
    GetAllDataService getAllData;

    @Autowired
    RequestPetitionService requestPetitionService;

    @Autowired
    FindDogService findDogService;

    @Autowired
    ShowWalkPetitionService showWalkPetitionService;

    @Autowired
    ProposePriceService proposePrice;

    @Autowired
    CreateNotificationService createNotificationService;
    

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
    public  WalkPetition_Dto proposePrice(@Valid @RequestBody WalkPetition_Dto walkPetition_Dto){
        WalkPetition petition = proposePrice.proposePrice(walkPetition_Dto);
        Entero dog_id= new Entero(walkPetition_Dto.getDog_id());
        String dogName=findDogService.find(dog_id).getDog_name();
        if(petition != null){
            
            createNotificationService.createNotification(new Notification(null, "Tienes una propuesta de precio",
                    "El paseador " + walkPetition_Dto.getWalk_petition_walker_user() + " quiere pasear a tu perro " +
                            dogName + ". Dirígete a la pesataña de paseos pendientes.", "No leido",
                    petition.getUser(), null));
        }
        return walkPetition_Dto;
    }

    @PostMapping("/denyoraccept")
    public  Dog_Dto denyOrAcceptPetition(@Valid @RequestBody WalkInvoice_Dto walkInvoice_Dto){
        Boolean respesuta= requestPetitionService.denyOrAcceptPetition(walkInvoice_Dto);//ya que esto devuelve siempre null, se envía un anotificación de que hay una actualización en la negociación del precio de tu paseo
        String decision;
        if(respesuta) decision="Aceptada";
        else decision="Denegada";
        createNotificationService.createNotification(new Notification(null, "Tienes una actulización en el precio de tu paseo",
                "La propuesta del precio del paseo del perro " + walkInvoice_Dto.getDog_name() + " ha sido "+ decision+".", "No leido", walkInvoice_Dto.getDog_walker_id(), null));
        return null;
    }

}

