package com.petsuite.Services.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "dog_daycare_service")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DogDaycareService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dog_daycare_service_id;

    @NotBlank
    private String dogdaycare_Service_Name;

    @NotBlank
    private String dogdaycare_Service_Description;

    @NotNull
    private float dogdaycare_Service_Price;

    @NotBlank
    private String user;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="user", nullable=false,updatable = false, insertable = false)
    private DogDaycare dogDaycareServices;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "dogDaycareService")
    private Set<DogDayCareService_DogDayCareInvoice> dogDayCareService_dogDayCareInvoices2;

}

