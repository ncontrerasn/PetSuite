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
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dog_walker")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DogWalker extends InfoUser{
   
     
    @NotBlank
    private String dog_walker_name;
    
    @NotBlank
    private String dog_walker_phone;
    
    @NotBlank
    private String dog_walker_e_mail;
    
    @NotNull
    private Float dog_walker_score;
    
    @OneToMany(mappedBy = "dogWalker")
    private Set<WalkInvoice> walkInvoices;
    
    
    
    
}
