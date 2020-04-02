package com.petsuite.Services.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class DogDaycare {
    
    @Id
    private String dog_daycare_user;
    
    @NotBlank
    private String dog_daycare_name;
    
    private String dog_daycare_e_mail;
    
    @NotBlank
    private String dog_daycare_address;
    
    @NotBlank
    private boolean dog_daycare_type;
    
    @NotNull
    private Integer dog_daycare_phone;
    
    @NotNull
    private float dog_daycare_score;
    
    @OneToMany(mappedBy = "dogDaycare")
    private Set<DogDaycareService> dogDaycareServices;
    
    @OneToMany(mappedBy = "dogDaycare")
    private Set<DogDaycareInvoice> dogDaycareInvoices;
    
}
