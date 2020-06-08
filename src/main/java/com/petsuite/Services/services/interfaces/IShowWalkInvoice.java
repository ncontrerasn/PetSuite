package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.WalkInvoice;

import java.util.List;
import java.util.Optional;

public interface IShowWalkInvoice {

    List<Optional<Dog>> PendingDogList(Cadena dogWalker);
    List<Optional<Dog>> CompletedDogList(Cadena dogWalker);
    List<WalkInvoice> invoicesByStatus(Cadena cadena);
    List<WalkInvoice> invoicesByWalker(Cadena cadena);
    List<WalkInvoice> findByWalkerAndStatus(CadenaDoble cadenaDoble);
    List<WalkInvoice_Dto> findByStatusAccepted(Cadena cadena);
    List<WalkInvoice_Dto> findByStatusProgress(Cadena cadena);
    List<WalkInvoice> findByStatusEndedWalker(Cadena cadena);
    List<WalkInvoice_Dto> findByStatusEndedClient(Cadena cadena);


}
