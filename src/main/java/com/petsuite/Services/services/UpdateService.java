package com.petsuite.Services.services;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.WalkInvoice;
import com.petsuite.Services.repository.*;
import com.petsuite.Services.services.interfaces.iUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateService implements iUpdate {

    @Autowired
    InfoUserRepository infoUserRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    DogDaycareRepository dogDaycareRepository;

    @Autowired
    WalkInvoiceRepository walkInvoiceRepository;

    public Integer updateClientAddress(String user, String address){

        int Worked = 0;

        Worked = clientRepository.updateAddressByUser(address,user);

        return Worked;

    }

    public Integer updateUserPassword(String user, String password){

        if (password!=null)
        {
            if(!password.isEmpty())
            {
                int Worked = 0;

                Worked = infoUserRepository.updateUserPassword(password,user);

                return Worked;
            }
        }

        return 0;

    }

    public Integer updateName(String user, String name){

        int Worked = 0;

        Worked = infoUserRepository.updateClientName(name,user);

        return Worked;

    }

    public Integer updatePhone(String user, String Phone){

        int Worked = 0;

        Worked = infoUserRepository.updateClientPhone(Phone,user);

        return Worked;

    }

    public Integer updateMail(String user, String Mail){

        int Worked = 0;

        Worked = infoUserRepository.updateClientEmail(Mail,user);

        return Worked;

    }

    public Integer updateRace(Integer dog_id, String race){

        int Worked = 0;

        Worked = dogRepository.updateRace(race,dog_id);

        return Worked;

    }

    public Integer updateNotes(Integer dog_id, String notes){

        int Worked = 0;

        Worked = dogRepository.updateNotes(notes,dog_id);

        return Worked;

    }

    public Integer updateName(Integer dog_id, String name){

        int Worked = 0;

        Worked = dogRepository.updateName(name,dog_id);

        return Worked;

    }

    public Integer updateWeight(Integer dog_id, float weight){

        int Worked = 0;

        Worked = dogRepository.updateWeight(weight,dog_id);

        return Worked;

    }

    public Integer updateHeight(Integer dog_id, float height){

        int Worked = 0;

        Worked = dogRepository.updateHeight(height,dog_id);

        return Worked;

    }

    public Integer updateAge(Integer dog_id, Integer age){

        int Worked = 0;

        Worked = dogRepository.updateAge(age,dog_id);

        return Worked;

    }

    public Integer updateType(String user, Boolean type){

        int Worked = 0;

        Worked = dogDaycareRepository.updateType(type,user);

        return Worked;

    }

    public Integer updateDayCareAddress(String user, String address){

        int Worked = 0;

        Worked = dogDaycareRepository.updateAddress(address,user);

        return Worked;

    }

    public Integer updatePrice(String user, Float price){

        int Worked = 0;

        Worked = dogDaycareRepository.updatePrice(price,user);

        return Worked;

    }

    public Integer updateTax(String user, Float tax){

        int Worked = 0;

        Worked = dogDaycareRepository.updateTax(tax,user);

        return Worked;

    }

    @Override
    public Client_Dto UpdateClient(Client_Dto user_dto) {

        Client_Dto Cli_Dto = user_dto;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(user_dto.getUser());

        if (checkUser!=null)
        {

            uppdateReturns = updateUserPassword(user_dto.getUser(),user_dto.getPassword());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setPassword(null);
            }
            Cli_Dto.setPassword(null);

            uppdateReturns = updateClientAddress(user_dto.getUser(),user_dto.getClient_address());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_address(null);
            }

            uppdateReturns = updateName(user_dto.getUser(),user_dto.getClient_name());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_name(null);
            }

            uppdateReturns = updatePhone(user_dto.getUser(),user_dto.getClient_phone());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_phone(null);
            }

            uppdateReturns = updateMail(user_dto.getUser(),user_dto.getClient_e_mail());

            if (uppdateReturns!=1)
            {
                Cli_Dto.setClient_e_mail(null);
            }

        }else{
            Cli_Dto = new Client_Dto();
        }

        Cli_Dto.setRole(user_dto.getRole());
        Cli_Dto.setToken(user_dto.getToken());

        return Cli_Dto;
    }

    @Override
    public Dog_Dto UpdateDog(Dog_Dto dog) {

        Dog_Dto dogDTO = dog;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(dogDTO.getClient_id());

        if (checkUser!=null)
        {

            uppdateReturns = updateAge(dogDTO.getDog_id(),dogDTO.getDog_age());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_age(null);
            }

            uppdateReturns = updateName(dogDTO.getDog_id(),dogDTO.getDog_name());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_name(null);
            }

            uppdateReturns = updateHeight(dogDTO.getDog_id(),dogDTO.getDog_height());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_height(Float.parseFloat(null));
            }

            uppdateReturns = updateWeight(dogDTO.getDog_id(),dogDTO.getDog_weight());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_weight(Float.parseFloat(null));
            }

            uppdateReturns = updateNotes(dogDTO.getDog_id(),dogDTO.getDog_notes());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_notes(null);
            }

            uppdateReturns = updateRace(dogDTO.getDog_id(),dogDTO.getDog_race());

            if (uppdateReturns!=1)
            {
                dogDTO.setDog_race(null);
            }

        }else{
            dogDTO = new Dog_Dto();
        }

        return dogDTO;
    }

    @Override
    public DogDayCare_Dto UpdateDayCare(DogDayCare_Dto user_dto) {

        DogDayCare_Dto DayCare_DTO = user_dto;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(user_dto.getUser());

        if (checkUser!=null)
        {

            uppdateReturns = updateUserPassword(user_dto.getUser(),user_dto.getPassword());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setPassword(null);
            }
            DayCare_DTO.setPassword(null);
            uppdateReturns = updateDayCareAddress(user_dto.getUser(),user_dto.getDog_daycare_address());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_address(null);
            }

            uppdateReturns = updateName(user_dto.getUser(),user_dto.getDog_daycare_name());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_name(null);
            }

            uppdateReturns = updatePhone(user_dto.getUser(),user_dto.getDog_daycare_phone());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_phone(null);
            }

            uppdateReturns = updateMail(user_dto.getUser(),user_dto.getDog_daycare_e_mail());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_e_mail(null);
            }

            uppdateReturns = updateType(user_dto.getUser(),user_dto.getDog_daycare_type());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_type(null);
            }

            uppdateReturns = updatePrice(user_dto.getUser(),user_dto.getDog_daycare_price_base());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_price_base(null);
            }

            uppdateReturns = updateTax(user_dto.getUser(),user_dto.getDog_daycare_tax());

            if (uppdateReturns!=1)
            {
                DayCare_DTO.setDog_daycare_tax(null);
            }

        }else{
            DayCare_DTO = new DogDayCare_Dto();
        }
        DayCare_DTO.setRole(user_dto.getRole());
        DayCare_DTO.setToken(user_dto.getToken());

        return DayCare_DTO;
    }

    @Override
    public DogWalker_Dto UpdateDogWalker(DogWalker_Dto user_dto) {

        DogWalker_Dto DogWalk_DTO = user_dto;

        int uppdateReturns = 0;

        String checkUser = infoUserRepository.findUser(user_dto.getUser());

        if (checkUser!=null)
        {

            uppdateReturns = updateUserPassword(user_dto.getUser(),user_dto.getPassword());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setPassword(null);
            }
            DogWalk_DTO.setPassword(null);
            uppdateReturns = updateName(user_dto.getUser(),user_dto.getDog_walker_name());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setDog_walker_name(null);
            }

            uppdateReturns = updatePhone(user_dto.getUser(),user_dto.getDog_walker_phone());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setDog_walker_phone(null);
            }

            uppdateReturns = updateMail(user_dto.getUser(),user_dto.getDog_walker_e_mail());

            if (uppdateReturns!=1)
            {
                DogWalk_DTO.setDog_walker_e_mail(null);
            }

        }else{
            DogWalk_DTO = new DogWalker_Dto();
        }
        DogWalk_DTO.setRole(user_dto.getRole());
        DogWalk_DTO.setToken(user_dto.getToken());
        return DogWalk_DTO;
    }

    

}
