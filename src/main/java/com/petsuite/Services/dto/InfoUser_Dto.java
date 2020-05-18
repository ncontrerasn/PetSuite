package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoUser_Dto {

    private String user;
    private String password;
    private String role;//tipo 1 cliente, tipo 2 paseador, tipo 3 paseador;

}

