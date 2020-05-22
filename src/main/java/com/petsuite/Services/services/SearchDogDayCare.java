package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareInvoice;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.basics.Cadena;
import com.petsuite.basics.Entero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchDogDayCare implements ISearchDogDayCare{

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Override
    public List<DogDayCare_Dto> searchDayCareByName(Cadena name){

        System.out.println(name);

        List<String> usersToReturns = new ArrayList<>();

        List<String> findings = new ArrayList<>();

        String[] Words = name.getCadena().split(" ");

        for (int i=0; i<Words.length; i++){
            System.out.println(Words);
            findings = dogDaycareRepository.searchByName("%"+Words[i]+"%");

            while(!findings.isEmpty()){
                if (!usersToReturns.contains(findings.get(0))) {
                    usersToReturns.add(findings.get(0));
                }
                findings.remove(0);
            }
        }

        List<DogDayCare_Dto> returns = new ArrayList<>();

        DogDayCare_Dto DTO;

        Optional<DogDaycare> DC;

        while(!usersToReturns.isEmpty()){

            DC = dogDaycareRepository.findById(usersToReturns.remove(0));

            System.out.println(DC.get().getUser());

            DTO = new DogDayCare_Dto();

            DTO.setDog_daycare_name(DC.get().getName());
            DTO.setDog_daycare_type(DC.get().getDog_daycare_type());
            DTO.setDog_daycare_score(DC.get().getDog_daycare_score());
            DTO.setDog_daycare_phone(DC.get().getPhone());
            DTO.setDog_daycare_address(DC.get().getDog_daycare_address());
            DTO.setUser(DC.get().getUser());
            DTO.setDog_daycare_e_mail(DC.get().getE_mail());
            DTO.setDog_daycare_price_base(DC.get().getDog_daycare_base_price());
            DTO.setDog_daycare_tax(DC.get().getDog_daycare_tax());

            returns.add(DTO);

        }

        return returns;

    }

}
