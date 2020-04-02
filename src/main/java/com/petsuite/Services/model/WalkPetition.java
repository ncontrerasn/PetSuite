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
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private String walk_petition_time;
    
    @NotBlank
    private String walk_petition_address;
    
    @NotBlank
    private String walk_petition_date;
    
    private String walk_petition_notes;
    
    @OneToOne(mappedBy = "client_p")
    private Client client;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id",updatable = false, insertable = false)
    private Dog dog_wp;
       
}
