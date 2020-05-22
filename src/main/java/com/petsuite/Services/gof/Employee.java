/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petsuite.Services.gof;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergi
 */

 
 
public class Employee implements IEmployed
{
    private List <IEmployed> _subordinates = new ArrayList<>();
    private String _name;
 
    public void AddSubordinate(IEmployed subordinate)
    {
        _subordinates.add(subordinate);
    }
 
    public void RemoveSubordinate(IEmployed subordinate)
    {
        _subordinates.remove(subordinate);
    }
 
    public IEmployed GetSubordinate(int index)
    {
        return _subordinates.get(index);
    }
    
    public List<IEmployed> getList(){
        
        return this._subordinates;
    }
 
   
 

    @Override
    public String getName() {
        
        
        return _name;
    }
    
  
    @Override
    public String setName(String name) {
        return this._name=name;
    }
}
 
 
