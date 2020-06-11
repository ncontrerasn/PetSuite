package com.petsuite.Services.services;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.services.interfaces.ILogin;
import com.petsuite.controller.TokenController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LoginService implements ILogin {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    TokenController tokenController;

    @Override
    public Object clientLogin(InfoUser_Dto user){
        
        
        String sqlA = "SELECT * FROM info_user where user = ?";
        String user_user = user.getUser();
        String user_password = user.getPassword();

        List<InfoUser> ul= jdbcTemplate.query(sqlA, new Object[]{user_user}, (rs, rowNum) -> new InfoUser(
                rs.getString("user"),
                rs.getString("e_mail"),
                rs.getString("phone"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("role"),
                null
        ));
        InfoUser u;
        
        if (!ul.isEmpty()){
            u = ul.get(0);
            if (u.getPassword().equals(user_password)){
                
                if("ROLE_CLIENT".equals(u.getRole())){
                    
                    String sqlC = "SELECT * FROM info_user natural join client where user = ?";
                    List<Client_Dto> ul2= jdbcTemplate.query(sqlC, new Object[]{user.getUser()}, (rs, rowNum) -> new Client_Dto(
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("e_mail"),
                            rs.getString("client_address")
                    ));

                    if(ul2.get(0)!=null)
                    {
                        user.setRole(u.getRole());
                        String token= tokenController.generate(user);
                        ul2.get(0).setUser(u.getUser());
                        ul2.get(0).setToken(token);
                        ul2.get(0).setRole(u.getRole());


                        return ul2.get(0);
                    }
                }
                if("ROLE_DOGWALKER".equals(u.getRole())){
                    String sqlP = "SELECT * FROM info_user natural join dog_walker where user = ?";
                    List<DogWalker_Dto> ul2= jdbcTemplate.query(sqlP, new Object[]{user.getUser()}, (rs, rowNum) -> new DogWalker_Dto(
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("e_mail"),
                            rs.getFloat("dog_walker_score")
                    ));
                    if(ul2.get(0)!=null)
                    {
                        user.setRole(u.getRole());
                        String token = tokenController.generate(user);
                        ul2.get(0).setToken(token);
                        ul2.get(0).setUser(u.getUser());
                        ul2.get(0).setRole(u.getRole());

                        return ul2.get(0);
                    }
                }
                if("ROLE_DOGDAYCARE".equals(u.getRole())){

                    String sqlG = "SELECT * FROM info_user natural join dog_daycare where user = ?";
                    List<DogDayCare_Dto> ul2= jdbcTemplate.query(sqlG, new Object[]{user.getUser()}, (rs, rowNum) -> new DogDayCare_Dto(
                            rs.getString("e_mail"),
                            rs.getString("dog_daycare_address"),
                            rs.getBoolean("dog_daycare_type"),
                            rs.getString("phone"),

                            rs.getFloat("dog_daycare_score"),

                            rs.getString("name"),
                            rs.getFloat("dog_daycare_base_price"),
                            rs.getFloat("dog_daycare_tax")
                    ));

                    if(ul2.get(0)!=null)
                    {
                        user.setRole(u.getRole());
                        String token = tokenController.generate(user);
                        ul2.get(0).setToken(token);
                        ul2.get(0).setUser(u.getUser());
                        ul2.get(0).setRole(u.getRole());
                        return ul2.get(0);
                    }
                }
            }
        }
        return null;
    }
}
