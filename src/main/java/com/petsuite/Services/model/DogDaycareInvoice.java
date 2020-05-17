package com.petsuite.Services.model;

import java.time.LocalDateTime;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

    @NotNull
    private LocalDateTime dog_daycare_invoice_date;

    @NotNull
    private Float dog_daycare_invoice_duration;

    @NotNull
    private Float dog_daycare_invoice_price;

    private Boolean dog_daycare_invoice_status;

    @NotBlank
    private String dog_daycare_id;

    @NotBlank
    private String client_id ;

    @NotNull
    private Integer dog_id;

    private Float dog_daycare_score;

    //@Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="dog_daycare_id", nullable=false,updatable = false, insertable = false)
    private DogDaycare dogDaycare;

    //@Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false,updatable = false, insertable = false)
    private Client client_i;

    @Getter(AccessLevel.NONE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id",updatable = false, insertable = false)
    private Dog dog;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "dogDaycareInvoice")
    private Set<DogDayCareService_DogDayCareInvoice> dogDayCareService_dogDayCareInvoices;

}