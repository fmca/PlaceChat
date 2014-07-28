/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.usuario;

import com.fuzuapp.model.usuario.entidades.Login;
import com.fuzuapp.model.usuario.entidades.Senha;
import com.fuzuapp.model.usuario.entidades.Usuario;
import com.fuzuapp.model.usuario.exceptions.AutenticacaoInvalida;

/**
 *
 * @author Filipe_2
 */
public class ControladorUsuario {
   
    private CadastroUsuario cadastroUsuario;
    
    public ControladorUsuario(IRepositorioUsuario repUsuario){
        this.cadastroUsuario = new CadastroUsuario(repUsuario);
    }
    public void cadastrar (Usuario usuario){
        this.cadastroUsuario.inserirUsuario(usuario);
    }
    
    public void autenticar (Login login, Senha senha) throws AutenticacaoInvalida{
        
        cadastroUsuario.autenticar(login, senha);
    }

    public Usuario buscarUsuario(Login login){
        return cadastroUsuario.getUsuario(login);
    }
}
