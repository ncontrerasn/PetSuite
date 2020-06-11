package com.petsuite.controller;

import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.Notification;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;
import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Cancellation_Dto;
import com.petsuite.Services.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/walkinvoices")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class WalkInvoiceController {

    @Autowired
    GetAllData getAllData;

    @Autowired
    CreateInvoiceService createInvoiceService;

    @Autowired
    QualifyService qualifyService;

    @Autowired
    ShowWalkInvoiceService showWalkInvoiceService;

    @Autowired
    FindDogService findDogService;

    @Autowired
    UpdateService updateService;

    @Autowired
    ChangeStatusRequestPetitionService changeStatusRequestPetitionService;
    
    @Autowired
    CancelRequestPetitionService cancelRequestPetitionService;

    @Autowired
    CreateNotificationService createNotificationService;

    @GetMapping("/all")
    public List<WalkInvoice> getAllInvoices() { return getAllData.getAllWalkInvoices(); }

    @PostMapping("/create")
    public WalkInvoice createInvoice(@Valid @RequestBody WalkInvoice_Dto walkinvoice){ return createInvoiceService.createWalkInvoice(walkinvoice); }

    @PostMapping("/score")//para pedir otro paseo debe estar calificado al paseador. Pero para comenzar un paseo, se hace un recibo con status 0, cuando se califica al paseador, se pone status 1, cuando se califica ya es porque se ha terminado el paseo
    public Cadena scoreDogWalker(@Valid @RequestBody WalkInvoice_Dto walkInvoice_dto){ return qualifyService.scoreDogWalker(walkInvoice_dto); }

    @PostMapping("/invoicesByStatus")
    public List<WalkInvoice> invoicesByStatus(@Valid @RequestBody Cadena cadena){ return showWalkInvoiceService.invoicesByStatus(cadena); }

    @PostMapping("/invoicesByWalker")//devuleve todos los recibos sin importar el estado
    public List<WalkInvoice> invoicesByWalker(@Valid @RequestBody Cadena cadena){ return showWalkInvoiceService.invoicesByWalker(cadena); }

    @PostMapping("/invoicesByWalkerAndStatus")//devuleve todos los recibos de paseos con un estado especifico. Se podria unir con el de arriba
    public List<WalkInvoice> findByWalkerAndStatus(@Valid @RequestBody CadenaDoble cadenaDoble){ return showWalkInvoiceService.findByWalkerAndStatus(cadenaDoble); }

    @PostMapping("/invoicesAccepted")
    public List<WalkInvoice_Dto> findByStatusAccepted(@Valid @RequestBody Cadena cadena){ return showWalkInvoiceService.findByStatusAccepted(cadena); }

    @PostMapping("/invoicesProgress")
    public List<WalkInvoice_Dto> findByStatusProgress(@Valid @RequestBody Cadena cadena){ return showWalkInvoiceService.findByStatusProgress(cadena); }

    @PostMapping("/invoicesEndedWalkers")
    public List<WalkInvoice> findByStatusEndedWalker(@Valid @RequestBody Cadena cadena){ return showWalkInvoiceService.findByStatusEndedWalker(cadena); }

    @PostMapping("/invoicesEndedClient")
    public List<WalkInvoice_Dto> findByStatusEndedClient(@Valid @RequestBody Cadena cadena){ return showWalkInvoiceService.findByStatusEndedClient(cadena); }

    @PostMapping("/dogsByWalkerAndStatusProgress")
    public List<Dog> findDogsByWalkerAndStatusAccepted(@Valid @RequestBody Cadena cadena){ return findDogService.findDogsByWalkerAndStatusAccepted(cadena); }

    @PostMapping("/updateInvoiceStatus")
    public List<WalkInvoice> updateInvoiceStatus(@Valid @RequestBody Entero entero) throws InterruptedException{
        List<WalkInvoice> walkInvoices = changeStatusRequestPetitionService.updateWalkInvoiceStatus(entero);
        int dogId = walkInvoices.get(0).getDog_id();
        Entero entero1 = new Entero(dogId);
        Dog_Dto dog = findDogService.find(entero1);
        createNotificationService.createNotification(new Notification(null, "Tienes una actulización en el estado de uno de tus paseos",
                "El estado del paseo de tu perro " + dog.getDog_name() +" ha sido actualizado a " + walkInvoices.get(0).getWalk_invoice_status() +".", "No leido", walkInvoices.get(0).getClient_id(), null));
        return walkInvoices;
    }

    @PostMapping(value = "/cancelPetition")
    public Boolean cancelPetition(@Valid @RequestBody Cancellation_Dto cancellation_Dto){
        boolean res =cancelRequestPetitionService.cancelWalk(cancellation_Dto);
        createNotificationService.createNotification(new Notification(null, "Se ha cancelado uno de tus paseos",
                cancellation_Dto.getUser_whoCancel() +" ha cancelado el paseo que tenía pactado contigo.", "No leido", cancellation_Dto.getUser_Cancelled(), null));
        return res;
    }
    

}

