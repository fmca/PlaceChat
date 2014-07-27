/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario;

import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filipe_2
 */
public class RepositorioList implements IRepositorioUsuario{

    List<Usuario> usuarios = new ArrayList();
    
    @Override
    public void inserir(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public void remover(Usuario usuario) {
        for(Usuario u : usuarios){
            if(u.getLogin().equals(usuario)){
                this.usuarios.remove(u);
                break;
            }
        }
    }

    @Override
    public Usuario get(Login login) {
        try {
            for (Usuario u : usuarios) {
                if (u.getLogin().toString().equals(login.toString())) {
                    return u;
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        
        return null;
    }
    
}
