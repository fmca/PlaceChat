/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario.entidades;

import com.fuzuapp.model.usuario.exceptions.EmailInvalidoException;

/**
 *
 * @author Filipe_2
 */
public class Email {
       private String str;
       public Email(){
           
       }
    
    public Email(String str) throws EmailInvalidoException{
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
    public void setStr(String str) throws EmailInvalidoException {
        if(str==null || str.trim().length()<=1) throw new EmailInvalidoException("Email não pode ser nulo.");
        this.str = str;
    }

    @Override
    public String toString() {
        return this.getStr();
    }
    
}
