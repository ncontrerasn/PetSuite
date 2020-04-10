package com.petsuite.security;

import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "youtube";

    public InfoUser_Dto validate(String token) {

        InfoUser_Dto jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new InfoUser_Dto(body.getSubject(),(String) body.get("userPassword"), (String) body.get("role"));

          
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
