/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario.entidades;

import com.fuzuapp.model.usuario.exceptions.LoginInvalidoException;

/**
 *
 * @author Filipe_2
 */
public class Login {
       private String str;
    public Login(){
           
       }
    public Login(String str) throws LoginInvalidoException{
        this.setStr(str);
    }

    /**
     * @return the str
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) throws LoginInvalidoException {
        if(str==null || str.trim().length()<=1) throw new LoginInvalidoException("Email não pode ser nulo.");
        this.str = str;
    }

    @Override
    public String toString() {
        return this.getStr();
    }
    
}
