package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Dog_Dto {

    private Integer dog_id;
    private String dog_name;
    private String dog_race;
    private float dog_height;
    private float dog_weight;
    private Integer dog_age;
    private String dog_notes;
    private String client_id;

}
