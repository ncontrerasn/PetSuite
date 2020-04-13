package com.petsuite.controller;

import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.JwtUser;
import com.petsuite.security.JwtGenerator;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
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
