package com.petsuite.security;

import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {


    public String generate(InfoUser_Dto jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUser());
        claims.put("userPassword", String.valueOf(jwtUser.getPassword()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
