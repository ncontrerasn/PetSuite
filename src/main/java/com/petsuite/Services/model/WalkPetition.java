package com.petsuite.Services.model;

import javax.persistence.*;
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
@Table(name = "walk_petition")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WalkPetition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walk_petition_id;
    
    @NotNull
    private LocalDateTime walk_petition_date_time;
    
    @NotBlank
    private String walk_petition_address;
    
    @NotNull
    private Float walk_petition_duration;
    
    private String walk_petition_notes;
    
    private String walk_petition_walker_user;

    @NotBlank
    private String user;

    @NotNull
    private Integer dog_id;
    
    private Integer price;
    
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "user",updatable = false, insertable = false)
    private Client client_p;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id",updatable = false, insertable = false)
    private Dog dog_wp;

    public WalkPetition(LocalDateTime walk_petition_date_time, String walk_petition_address, Float walk_petition_duration, String walk_petition_notes, String user, Integer dog_id, Object o, Object o1) {
        this.walk_petition_date_time = walk_petition_date_time;
        this.walk_petition_address = walk_petition_address;
        this.walk_petition_duration = walk_petition_duration;
        this.walk_petition_notes = walk_petition_notes;
        this.user = user;
        this.dog_id = dog_id;
    }
}
