package com.petsuite.Services.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dog_daycare")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DogDaycare extends InfoUser{
    
    private String dog_daycare_e_mail;
    @NotBlank
    private String dog_daycare_name;
       
    @NotBlank
    private String dog_daycare_address;
    
    @NotNull
    private Boolean dog_daycare_type;
    
    @NotBlank
    private String dog_daycare_phone;
    
    @NotNull
    private Float dog_daycare_score;
    
    @OneToMany(mappedBy = "dogDaycare")
    private Set<DogDaycareService> dogDaycareServices;
    
    @OneToMany(mappedBy = "dogDaycare")
    private Set<DogDaycareInvoice> dogDaycareInvoices;
    
}
