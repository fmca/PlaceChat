package com.fuzuapp.model.usuario.exceptions;

/**
 * Created by filipe on 28/07/14.
 */
public class UsuarioJaExisteException extends Exception {

    public UsuarioJaExisteException(){
        super("Usuário já existe");
    }
}
