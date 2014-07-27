/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario;

import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Usuario;

/**
 *
 * @author Filipe_2
 */
public interface IRepositorioUsuario {
    void inserir(Usuario usuario);
    void remover(Usuario usuario);
    Usuario get(Login login);
}
