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
@Table(name = "walk_invoice")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WalkInvoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walk_invoice_id;
    
    @NotBlank
    private String walk_invoice_time;
    
    @NotBlank
    private String walk_invoice_address;
    
    @NotBlank
    private String walk_invoice_date;
    
    @NotNull
    private float walk_invoice_price;
    
    private boolean walk_invoice_status;
    
    @NotBlank
    private String client_user;
    
    @NotBlank
    private String dog_walker_user;
    
    @ManyToOne
    @JoinColumn(name="client_user", nullable=false,updatable = false, insertable = false)
    private Client client_wi;
    
    @ManyToOne
    @JoinColumn(name="dog_walker_user", nullable=false,updatable = false, insertable = false)
    private DogWalker dogWalker;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id",updatable = false, insertable = false)
    private Dog dog;
    
}
