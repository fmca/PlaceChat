/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fuzuapp.model.usuario.entidades;

import com.fuzuapp.model.usuario.exceptions.SenhaInvalidaException;

/**
 *
 * @author Filipe_2
 */
public class Senha {

    private String str;

    public Senha() {

    }

    public Senha(String str) throws SenhaInvalidaException {
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
    public void setStr(String str) throws SenhaInvalidaException {
        if (str == null || str.trim().length() <= 1) {
            throw new SenhaInvalidaException("Senha não pode ser nula");
        }
        this.str = str;
    }

    @Override
    public String toString() {
        return this.getStr();
    }
}
