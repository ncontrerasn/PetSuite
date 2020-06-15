package com.petsuite.Services.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

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

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "infoUser_d")
    private Set<Notification> notifications;

    public InfoUser(String user, String e_mail, String phone, String password, String name, String role) {
        this.user = user;
        this.e_mail = e_mail;
        this.phone = phone;
        this.password = password;
        this.name = name;
    }
}
