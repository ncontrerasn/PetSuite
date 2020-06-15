package com.petsuite.Services.services;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.controller.TokenController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

class LoginServiceTest {

    @InjectMocks
    LoginService loginService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    TokenController tokenController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void clientLogin()
    {
        InfoUser user = new InfoUser();

        user.setE_mail("htovars@unal.edu.co");
        user.setName("hubert");
        user.setPassword("1223");
        user.setPhone("31546272289");
        user.setRole("ROLE_CLIENT");
        user.setUser("htovars");

        List<InfoUser> userList = new ArrayList<>();

        userList.add(user);

        InfoUser_Dto user_dto = new InfoUser_Dto();

        user_dto.setUser("htovars");
        user_dto.setPassword("1223");
        user_dto.setRole("ROLE_CLIENT");

        Client_Dto client_Dto = new Client_Dto();

        client_Dto.setClient_e_mail("htovars@unal.edu.co");
        client_Dto.setClient_name("hubert");
        client_Dto.setClient_phone("31546272289");
        client_Dto.setClient_address("La candelaria");
        client_Dto.setPassword("1223");
        client_Dto.setRole("ROLE_CLIENT");
        client_Dto.setUser("htovars");

        List<Client_Dto> client_Dto_list = new ArrayList<>();

        client_Dto_list.add(client_Dto);

        when(jdbcTemplate.query(anyString(),new Object[]{any()}, (rs, rowNum) -> new InfoUser(
                rs.getString("user"),
                rs.getString("e_mail"),
                rs.getString("phone"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("role"),
                null
        ))).thenReturn(userList);

        when(jdbcTemplate.query(anyString(),new Object[]{any()},(rs, rowNum) -> new Client_Dto(
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("e_mail"),
                rs.getString("client_address")
        ))).thenReturn(client_Dto_list);

        when(tokenController.generate(any())).thenReturn("token");

        assertEquals("token",loginService.clientLogin(user_dto));

    }
}