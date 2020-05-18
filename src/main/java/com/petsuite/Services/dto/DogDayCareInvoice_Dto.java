package com.petsuite.Services.dto;

import java.util.List;
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
    private String dog_daycare_invoice_status;
    private String dog_daycare_invoice_dogdaycare_id;
    private String dog_daycare_invoice_client_id;
    private Integer dog_daycare_invoice_dog_id;
    private List<Integer> dog_daycare_invoice_services;
    private Float dog_daycare_invoice_score;
    private List<String> dog_daycare_invoice_services_names;
    private String dog_daycare_invoice_dog_name;

}

