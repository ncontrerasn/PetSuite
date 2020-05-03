/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sergi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkInvoice_Dto {


    private Integer walk_invoice_id;
    private float walk_invoice_price;

    private String walk_invoice_status;

    private String client_id;

    private String  dog_walker_id;

    private Integer dog_id;
    private Float walker_score;

}
