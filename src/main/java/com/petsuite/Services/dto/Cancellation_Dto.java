package com.petsuite.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancellation_Dto {

    private Integer id_petition;
    private String user_Cancelled;
    private String user_whoCancel;
    private String reasonCancellation;
    



}

