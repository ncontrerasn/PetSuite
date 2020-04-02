package com.petsuite.model;

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
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    
    @Id
    private String client_user;
    
    @NotBlank
    private String client_password;
    
    @NotBlank
    private String client_name;
    
    @NotNull
    private Integer client_phone;
    
    @NotBlank
    private String client_e_mail;
    
    @NotBlank
    private String client_address;
    
    @OneToMany(mappedBy = "client_d")
    private Set<Dog> dogs;
    
    @OneToMany(mappedBy = "client_wi")
    private Set<WalkInvoice> walkInvoices;

    @OneToMany(mappedBy = "client_i")
    private Set<DogDaycareInvoice> dogDaycareInvoices;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "walk_petition_id", referencedColumnName = "walk_petition_id",updatable = false, insertable = false)
    private WalkPetition client_p;
}