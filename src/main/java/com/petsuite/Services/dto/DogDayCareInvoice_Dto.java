package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogDayCareInvoice_Dto {

   
    private String dog_daycare_invoice_date;
    private Float dog_daycare_invoice_duration;
    private Float dog_daycare_invoice_price;
    private Boolean dog_daycare_invoice_status;
    private String dog_daycare_invoice_dogdaycare_id;
    private String dog_daycare_invoice_client_id;
    private Integer dog_daycare_invoice_dog_id;
   



}

