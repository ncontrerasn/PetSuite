package com.petsuite.Services.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dog_daycare_service")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DogDaycareService {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dog_daycare_service_id;
    
    @NotBlank
    private String dog_daycare_invoice_name;
    
    private String dog_daycare_invoice_description;

    @NotNull
    private float dog_daycare_invoice_price;
    
    @NotBlank
    private String dog_daycare_user;
    
    @NotNull
    private Integer dog_daycare_invoice_id;
    
     @NotNull
    private Integer dog_daycare_id;
     
     
    
    
    
    @ManyToOne
    @JoinColumn(name="dog_daycare_id", nullable=false,updatable = false, insertable = false)
    private DogDaycare dogDaycare;
    
    @ManyToOne
    @JoinColumn(name="dog_daycare_invoice_id", nullable=false,updatable = false, insertable = false)
    private DogDaycareInvoice dogDaycareInvoice;

}
