package com.petsuite.Services.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petsuite.Services.compositeKey.Service_Invoice_Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.persistence.EmbeddedId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Entity
@Table(name = "service_invoice")
@EntityListeners(AuditingEntityListener.class)


public class DogDayCareService_DogDayCareInvoice {

    private Service_Invoice_Id service_invoice_id = new Service_Invoice_Id();

    private DogDaycareService dogDaycareService;

    private DogDaycareInvoice dogDaycareInvoice;
    private Integer dog_daycare_service_id;
    private Integer dog_daycare_invoice_id;
    
    public DogDayCareService_DogDayCareInvoice() {
    }


    
    
    @EmbeddedId
    @JsonIgnore
    public Service_Invoice_Id getId(){
        return service_invoice_id;
    }

    public void setId(Service_Invoice_Id service_invoice_id){ 
        this.service_invoice_id=service_invoice_id;
    }

    @MapsId("dog_daycare_service_id")
    @ManyToOne
    @JoinColumn(name="dog_daycare_service_id", nullable=false,referencedColumnName = "dog_daycare_service_id")
    @JsonIgnore
    public DogDaycareService getDogDaycareService() {
        return dogDaycareService;
    }

    public void setDogDaycareService(DogDaycareService dogDaycareService) {
        this.dogDaycareService = dogDaycareService;
    }

    @MapsId("dog_daycare_invoice_id")
    @ManyToOne
    @JoinColumn(name="dog_daycare_invoice_id", nullable=false,referencedColumnName = "dog_daycare_invoice_id")
    @JsonIgnore
    public DogDaycareInvoice getDogDaycareInvoice() {
        return dogDaycareInvoice;
    }

    public void setDogDaycareInvoice(DogDaycareInvoice dogDaycareInvoice) {
        this.dogDaycareInvoice = dogDaycareInvoice;
    }

}


