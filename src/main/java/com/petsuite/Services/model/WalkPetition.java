package com.petsuite.Services.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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
    
    @NotBlank
    private LocalDateTime walk_petition_date_time;
    
    @NotBlank
    private String walk_petition_address;
    
    @NotBlank
    private Float walk_petition_duration;
    
    private String walk_petition_notes;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "user",updatable = false, insertable = false)
    private Client client_p;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id",updatable = false, insertable = false)
    private Dog dog_wp;
       
}
