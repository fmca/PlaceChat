/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario.entidades;

import com.fuzuapp.model.usuario.exceptions.NomeInvalidoException;

/**
 *
 * @author Filipe_2
 */
public class Nome {
    
    private String str;
    
    public Nome(){
           
       }
    public Nome(String str) throws NomeInvalidoException{
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
    public void setStr(String str) throws NomeInvalidoException {
        if(str == null || str.trim().length()<=1) throw new NomeInvalidoException("Nome não pode ser nulo");
        else this.str = str;
    }

    @Override
    public String toString() {
        return this.getStr();
    }
    
    
}
