/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.gof;

import java.util.List;

/**
 *
 * @author sergi
 */
public class Contractor implements IEmployed
{
    private String _name;
 
    
    

   @Override
    public String getName() {
        return _name;
    }

    @Override
    public String setName(String name) {
        return this._name=name;
    }

  

    @Override
    public List<IEmployed> getList() {
       return null;
    }
}