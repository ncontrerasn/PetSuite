package com.petsuite.Services.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "dog_daycare_invoice")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DogDaycareInvoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dog_daycare_invoice_id;
    
    @NotBlank
    private String dog_daycare_invoice_time;
    
    @NotBlank
    private String dog_daycare_invoice_date;
    
    @NotNull
    private float dog_daycare_invoice_price;
    
    private boolean dog_daycare_invoice_status;
    
    @NotBlank
    private String dog_daycare_user;
    
    @NotBlank
    private String client_user;
    
    @ManyToOne
    @JoinColumn(name="dog_daycare_user", nullable=false,updatable = false, insertable = false)
    private DogDaycare dogDaycare;
    
    @ManyToOne
    @JoinColumn(name="client_user", nullable=false,updatable = false, insertable = false)
    private Client client_i;
    
    @OneToMany(mappedBy = "dogDaycareInvoice")
    private Set<DogDaycareService> dogDaycareServices;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id",updatable = false, insertable = false)
    private Dog dog;
    
}