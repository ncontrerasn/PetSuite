/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.dto.InfoUser_Dto;

/**
 *
 * @author sergi
 */
public interface ILogin {

    Object clientLogin(InfoUser_Dto user); //login de InFoUser

}
