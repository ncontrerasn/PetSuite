package com.petsuite.Services.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfoUser {
    
    @Id
    private String user;

    @NotBlank
    private String e_mail;

    @NotBlank
    private String phone;
    
    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotNull
    private String role;//tipo 1 cliente, tipo 2 paseador, tipo 3 paseador;

}
