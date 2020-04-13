package com.petsuite.controller;

import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.JwtUser;
import com.petsuite.security.JwtGenerator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {


    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final InfoUser_Dto jwtUser) {

        return jwtGenerator.generate(jwtUser);

    }
}
