package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.DogDayCareService_DogDayCareInvoice;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.*;
import com.petsuite.Services.services.interfaces.ICreateInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CreateInvoiceService implements ICreateInvoiceService {

    @Autowired
    ProposePriceService proposePrice;

    @Autowired
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Autowired
    Service_InvoiceRepository service_invoiceRepository;

    @Autowired
    WalkPetitionRepository walkPetitionRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    @Override
    public DogDayCareInvoice_Dto createDogDayCareInvoice(DogDayCareInvoice_Dto dogDaycareInovice)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dogDaycareInovice.getDog_daycare_invoice_date(), formatter);
        float price=proposePrice.requestPriceDogDayCareInvoice(dogDaycareInovice).getDog_daycare_invoice_price();
        DogDaycareInvoice daycareInvoice= new DogDaycareInvoice(null, dateTime,
                dogDaycareInovice.getDog_daycare_invoice_duration(), price, "Aceptado",
                dogDaycareInovice.getDog_daycare_invoice_dogdaycare_id(), dogDaycareInovice.getDog_daycare_invoice_client_id(),
                dogDaycareInovice.getDog_daycare_invoice_dog_id(), null, null, null,
                null, null);
        dogDaycareInovice.setDog_daycare_invoice_price(price);
        if(daycareInvoice!=null){
            DogDaycareInvoice dogDaycareInvoice = dogDaycareInvoiceRepository.saveAndFlush(daycareInvoice);
            //registro en la tabla intermedia
            for (int i = 0; i < dogDaycareInovice.getDog_daycare_invoice_services().size(); i++) {
                DogDayCareService_DogDayCareInvoice dogDayCareService_dogDayCareInvoice = new DogDayCareService_DogDayCareInvoice();
                dogDayCareService_dogDayCareInvoice.setDogDaycareInvoice(dogDaycareInvoice);
                dogDayCareService_dogDayCareInvoice.setDogDaycareService(dogDaycareServiceRepository.findById(
                        dogDaycareInovice.getDog_daycare_invoice_services().get(i)).get());
                service_invoiceRepository.save(dogDayCareService_dogDayCareInvoice);
            }
        }
        return dogDaycareInovice;
    }

    @Override
    public WalkInvoice createWalkInvoice(WalkInvoice_Dto walkinvoice)
    {
        WalkPetition WalkPetitions = walkPetitionRepository.findPetitionsByDogAndByUser(walkinvoice.getDog_id().toString(),walkinvoice.getClient_id());

        if (WalkPetitions!=null) {

            WalkInvoice walkinvoiceReal = new WalkInvoice(WalkPetitions.getWalk_petition_date_time(),
                    WalkPetitions.getWalk_petition_address(), WalkPetitions.getWalk_petition_duration(),
                    WalkPetitions.getWalk_petition_notes(), WalkPetitions.getUser(), WalkPetitions.getDog_id(),
                    walkinvoice.getDog_walker_id(), walkinvoice.getWalk_invoice_price(),walkinvoice.getWalk_invoice_status());

            walkPetitionRepository.deletePetition(WalkPetitions.getWalk_petition_id());

            walkinvoiceReal = walkInvoiceRepository.save(walkinvoiceReal);

            if (walkinvoiceReal != null)
                return walkinvoiceReal;
            else
                return null;
        }else
            return null;
    }
}
