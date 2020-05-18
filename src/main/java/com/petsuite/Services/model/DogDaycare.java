package com.petsuite.Services.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dog_daycare")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DogDaycare extends InfoUser{

    @NotBlank
    private String dog_daycare_address;

    @NotNull
    private Boolean dog_daycare_type;

    @NotNull
    private Float dog_daycare_score;

    @NotNull
    private Float dog_daycare_base_price;

    @NotNull
    private Float dog_daycare_tax;


    @Getter(AccessLevel.NONE)
    //@Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "dogDaycareServices")
    private Set<DogDaycareService> dogDaycareServices;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "dogDaycare")
    private Set<DogDaycareInvoice> dogDaycareInvoices;

}

