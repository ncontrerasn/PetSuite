package com.petsuite.Services.services;

import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.services.interfaces.IGetServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetServicesService implements IGetServicesService {

    @Autowired
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Override
    public List<DogDaycareService> getMyServices(String user) { return dogDaycareServiceRepository.findMyServicesByUser(user); }
}
