package com.petsuite.Services.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

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

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="user", nullable=false,updatable = false, insertable = false)
    private Client client_d;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "dog_wp")
    private WalkPetition walkPetition;

    @Getter(AccessLevel.NONE)//antes 1 m
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "dog")
    private Set<WalkInvoice> walkInvoices;
    
     @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client_i")
    private Set<DogDaycareInvoice> dogDaycareInvoices;


    public Dog(String dog_name, String dog_race, Float dog_height, Float dog_weight, Integer dog_age, String dog_notes, String client_id) {
        this.dog_name = dog_name;
        this.dog_race = dog_race;
        this.dog_height = dog_height;
        this.dog_weight = dog_weight;
        this.dog_age = dog_age;
        this.dog_notes = dog_notes;
        this.user = client_id;
    }

}

