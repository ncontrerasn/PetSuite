package com.petsuite.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dog")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dog_id;
    
    @NotBlank
    private String dog_name;
    
    @NotBlank
    private String dog_race;
    
    @NotNull
    private float dog_height;
    
    @NotNull
    private float dog_weight;
    
    @NotNull
    private Integer dog_age;
    
    private String dog_notes;
    
    @NotNull
    private String client_user;
    
    @ManyToOne
    @JoinColumn(name="client_user", nullable=false,updatable = false, insertable = false)
    private Client client_d;
    
    @OneToOne(mappedBy = "dog_wp")
    private WalkPetition walkPetition;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "walk_invoice_id", referencedColumnName = "walk_invoice_id",updatable = false, insertable = false)
    private WalkInvoice walkInvoice;
    
    @OneToOne(mappedBy = "dog")
    private Client client;
    
}
