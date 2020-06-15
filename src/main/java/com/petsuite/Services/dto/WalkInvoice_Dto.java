package com.petsuite.Services.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkInvoice_Dto {

    private Integer walk_invoice_id;
    private float walk_invoice_price;
    private String walk_invoice_status;
    private String client_id;
    private String  dog_walker_id;
    private String walk_invoice_notes;
    private Integer dog_id;
    private Float walker_score;
    private LocalDateTime walk_invoice_date;
    private String walk_invoice_address;
    private Float walk_invoice_duration;
    private String dog_name;
    private String dog_race;
    private float dog_height;
    private float dog_weight;

}

