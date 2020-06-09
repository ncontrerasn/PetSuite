package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.model.DogDaycareService;

import java.util.List;

public interface IGetServicesService {

    List<DogDaycareService> getMyServices(String user);

}
