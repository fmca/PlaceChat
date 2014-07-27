/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario.exceptions;

/**
 *
 * @author Filipe_2
 */
public class SenhaInvalidaException extends Exception{
    
    public SenhaInvalidaException(String erro){
        super(erro);
    }
}
