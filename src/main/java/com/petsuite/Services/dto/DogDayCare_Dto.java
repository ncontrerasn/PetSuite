package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogDayCare_Dto {

    private String user;
    private String password;
    private String dog_daycare_e_mail;
    private String dog_daycare_name;
    private String dog_daycare_address;
    private Boolean dog_daycare_type;
    private String dog_daycare_phone;
    private Float dog_daycare_score;
    private String token;
    private String role;
    public Float dog_daycare_price_base;
    public Float dog_daycare_tax;

    public DogDayCare_Dto(String dog_daycare_e_mail, String dog_daycare_address, Boolean dog_daycare_type, String dog_daycare_phone, Float dog_daycare_score, String dog_daycare_name, Float dog_daycare_price_base, Float dog_daycare_tax) {

        this.dog_daycare_e_mail = dog_daycare_e_mail;
        this.dog_daycare_address = dog_daycare_address;
        this.dog_daycare_type = dog_daycare_type;
        this.dog_daycare_phone = dog_daycare_phone;
        this.dog_daycare_score = dog_daycare_score;
        this.dog_daycare_name=dog_daycare_name;
        this.dog_daycare_price_base=dog_daycare_price_base;
        this.dog_daycare_tax=dog_daycare_tax;
    }

}

