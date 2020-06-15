package com.petsuite.Services.services;

import com.petsuite.Services.basics.Flotante;
import com.petsuite.Services.services.interfaces.ISearchDogDayCare;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.basics.Cadena;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchDogDayCareService implements ISearchDogDayCare{

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    public List<DogDayCare_Dto>  OrganizeDayCaresToReturn(List<String> usersToReturns)
    {
        List<DogDayCare_Dto> returns = new ArrayList<>();

        DogDayCare_Dto DTO;

        Optional<DogDaycare> DC;

        while(!usersToReturns.isEmpty())
        {

            DC = dogDaycareRepository.findById(usersToReturns.remove(0));

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

    @Override
    public List<DogDayCare_Dto> searchDayCareByNameAndService(Cadena word)
    {
        List<String> usersToReturns = new ArrayList<>();

        List<String> findings = new ArrayList<>();

        List<String> findings2 = new ArrayList<>();

        String[] Words = word.getCadena().split(" ");

        for (int i=0; i<Words.length; i++)
        {
            findings = dogDaycareRepository.searchByName("%"+Words[i]+"%");
            findings2 = dogDaycareRepository.searchByService("%"+Words[i]+"%");

            while(!findings.isEmpty())
            {
                if (!usersToReturns.contains(findings.get(0)))
                {
                    usersToReturns.add(findings.get(0));
                }
                findings.remove(0);
            }

            while(!findings2.isEmpty())
            {
                if (!usersToReturns.contains(findings2.get(0)))
                {
                    usersToReturns.add(findings2.get(0));
                }
                findings2.remove(0);
            }
        }

        return OrganizeDayCaresToReturn(usersToReturns);
    }

    @Override
    public List<DogDayCare_Dto> searchDayCareByName(Cadena names)
    {
        List<String> usersToReturns = new ArrayList<>();

        List<String> findings = new ArrayList<>();

        String[] Words = names.getCadena().split(" ");

        for (int i=0; i<Words.length; i++)
        {
            findings = dogDaycareRepository.searchByName("%"+Words[i]+"%");

            while(!findings.isEmpty())
            {
                if (!usersToReturns.contains(findings.get(0)))
                {
                    usersToReturns.add(findings.get(0));
                }
                findings.remove(0);
            }
        }

        return OrganizeDayCaresToReturn(usersToReturns);
    }

    @Override
    public List<DogDayCare_Dto> searchDayCareByService(Cadena services)
    {
        List<String> usersToReturns = new ArrayList<>();

        List<String> findings = new ArrayList<>();

        String[] Words = services.getCadena().split(" ");

        for (int i=0; i<Words.length; i++)
        {
            findings = dogDaycareRepository.searchByService("%"+Words[i]+"%");

            while(!findings.isEmpty())
            {
                if (!usersToReturns.contains(findings.get(0)))
                {
                    usersToReturns.add(findings.get(0));
                }
                findings.remove(0);
            }
        }

        return OrganizeDayCaresToReturn(usersToReturns);
    }

    @Override
    public List<DogDayCare_Dto> searchDayCareByScore(Flotante score)
    {

        List<String> usersToReturns = new ArrayList<>();

        List<String> findings = new ArrayList<>();

        findings = dogDaycareRepository.searchByScore(score.getFlotante()+0.5f,score.getFlotante()-0.5f);

        while(!findings.isEmpty())
        {
            if (!usersToReturns.contains(findings.get(0)))
            {
                usersToReturns.add(findings.get(0));
            }
            findings.remove(0);
        }

        return OrganizeDayCaresToReturn(usersToReturns);
    }

}
