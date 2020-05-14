package com.petsuite.Services.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

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
    private String dogdaycare_Service_Name;

    @NotBlank
    private String dogdaycare_Service_Description;

    @NotNull
    private float dogdaycare_Service_Price;
    
    @NotBlank
    private String user;
    
  /*  @NotNull
    private Integer dog_daycare_invoice_id;*/

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="user", nullable=false,updatable = false, insertable = false)
    private DogDaycare dogDaycareServices;
    
    
    @Getter(AccessLevel.NONE) 
    @OneToMany
    @JoinColumn(name="dogDaycareService", nullable=false,updatable = false, insertable = false)
    private Set<DogDayCareService_DogDayCareInvoice> dogDayCareService_dogDayCareInvoices;

}
