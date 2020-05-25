package com.petsuite.Services.model;

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
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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

    @NotNull
    private LocalDateTime walk_invoice_date;

    @NotNull
    private float walk_invoice_price;

    private String walk_invoice_status;

    @NotBlank
    private String walk_invoice_address;

    @NotNull
    private Float walk_invoice_duration;

    private String walk_invoice_notes;

    private Float walker_score;

    @NotBlank
    private String client_id;

    @NotBlank
    private String dog_walker_id;

    @NotNull
    private Integer dog_id;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false,updatable = false, insertable = false)
    private Client client_wi;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="dog_walker_id", nullable=false,updatable = false, insertable = false)
    private DogWalker dogWalker;

    @Getter(AccessLevel.NONE)//
    @Setter(AccessLevel.NONE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id",updatable = false, insertable = false)
    private Dog dog;

    public WalkInvoice(LocalDateTime walk_invoice_date, String walk_invoice_address,
                       Float walk_invoice_duration, String walk_invoice_notes,
                       String client_id, Integer dog_id, String dog_walker_id,
                       Float walk_invoice_price, String walk_invoice_status) {
        this.walk_invoice_date = walk_invoice_date;
        this.walk_invoice_address = walk_invoice_address;
        this.walk_invoice_duration = walk_invoice_duration;
        this.walk_invoice_notes = walk_invoice_notes;
        this.client_id = client_id;
        this.dog_id = dog_id;
        this.dog_walker_id = dog_walker_id;
        this.walk_invoice_price = walk_invoice_price;
        this.walk_invoice_status = walk_invoice_status;
    }

}


