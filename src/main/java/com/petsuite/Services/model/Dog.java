package com.petsuite.Services.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Float dog_height;
    
    @NotNull
    private Float dog_weight;
    
    @NotNull
    private Integer dog_age;
    
    private String dog_notes;

    @NotBlank
    private String user;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user", nullable=false,updatable = false, insertable = false)
    private Client client_d;
    
    @OneToOne(mappedBy = "dog_wp")
    private WalkPetition walkPetition;
    
    @OneToOne(mappedBy = "dog")
    private WalkInvoice walkInvoice;

    public Dog(Integer dog_id, String dog_name, String dog_race, Float dog_height, Float dog_weight, Integer dog_age, String dog_notes, String client_id) {
        this.dog_id = dog_id;
        this.dog_name = dog_name;
        this.dog_race = dog_race;
        this.dog_height = dog_height;
        this.dog_weight = dog_weight;
        this.dog_age = dog_age;
        this.dog_notes = dog_notes;
        this.user = client_id;
    }
    
    
    
}
