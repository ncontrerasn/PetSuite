package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogDayCare_Service_Dto {

   private String dogdaycare_Service_Name;
   private String dogdaycare_Service_Description;
   private float dogdaycare_Service_Price;
   private String dogdaycare_Service_ClientId;

}

