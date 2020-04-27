package com.petsuite.Services.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client extends InfoUser{

    @NotBlank
    private String client_address;
    @JsonManagedReference
    @OneToMany(mappedBy = "client_d")
    private Set<Dog> dogs;
    
    @OneToMany(mappedBy = "client_wi")
    private Set<WalkInvoice> walkInvoices;

    @OneToMany(mappedBy = "client_i")
    private Set<DogDaycareInvoice> dogDaycareInvoices;
    
    @OneToOne(mappedBy = "client_p")
    private WalkPetition walkPetition;

}