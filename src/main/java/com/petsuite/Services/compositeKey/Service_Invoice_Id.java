package com.petsuite.Services.compositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Service_Invoice_Id implements Serializable{

    private Integer dog_daycare_service_id;
    private Integer dog_daycare_invoice_id;

}
